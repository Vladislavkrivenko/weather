import dao.UserAuthorizationDao;
import dao.UserRegistrationDao;
import dto.UserDto;
import entity.UserEntity;
import mapper.UserMapper;
import mapper.UserMapperImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import service.UserAuthorizationService;
import service.UserRegistrationService;
import util.AppConfig;
import util.hashPass.HashPassword;

import java.util.Optional;

public class Main {
    public static void main(String[] args) throws Exception {
        // 1. Конфігурація Hibernate (AppConfig)
        AppConfig appConfig = new AppConfig();
        SessionFactory sessionFactory = appConfig.sessionFactory(appConfig.dataSource());

        // 2. DAO + Mapper + Service
        UserAuthorizationDao dao = new UserAuthorizationDao(sessionFactory);
        UserMapper mapper = new UserMapperImpl(); // згенерований MapStruct
        UserAuthorizationService service = new UserAuthorizationService(dao, mapper);

        // 3. Створюємо нового юзера і хешуємо пароль
        String login = "john1";
        String rawPassword = "12345";
        String hashedPassword = HashPassword.hashPassword(rawPassword);

        UserEntity newUser = new UserEntity();
        newUser.setLogin(login);
        newUser.setPassword(hashedPassword);

        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(newUser);
            tx.commit();
        }

        System.out.println("✅ Користувач збережений: " + newUser);

        // 4. Тестуємо логін з правильним паролем
        System.out.println("\n--- Тест: правильний пароль ---");
        service.login(login, "12345").ifPresentOrElse(
                user -> System.out.println("Успішний вхід: " + user),
                () -> System.out.println("❌ Логін не вдався")
        );

        // 5. Тестуємо логін з неправильним паролем
        System.out.println("\n--- Тест: неправильний пароль ---");
        service.login(login, "wrong-pass").ifPresentOrElse(
                user -> System.out.println("Успішний вхід: " + user),
                () -> System.out.println("❌ Логін не вдався")
        );

        sessionFactory.close();
    }
}
