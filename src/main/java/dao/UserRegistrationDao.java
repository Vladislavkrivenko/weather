package dao;

import entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;


@Slf4j
@Repository
public class UserRegistrationDao {
    private final SessionFactory sessionFactory;

    public UserRegistrationDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void registrationUser(UserEntity user) {
        if (user == null) {
            log.debug("user is null");
            throw new IllegalArgumentException("User cannot be null");
        }

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.persist(user);
                transaction.commit();
                log.debug("user has been saved successfully");
            } catch (Exception e) {
                if (transaction != null) transaction.rollback();
                log.error("save user exception", e);
                throw e;
            }
        }
    }
}
