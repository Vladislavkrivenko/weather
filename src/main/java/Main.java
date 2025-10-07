public class Main {
    public static void main(String[] args) {
//
//        AnnotationConfigApplicationContext context =
//                new AnnotationConfigApplicationContext(AppConfig.class, RestTemplateConf.class);
//
//        try {
//            LocationAddingService locationAddingService = context.getBean(LocationAddingService.class);
//            LocationSearchDao searchDao = context.getBean(LocationSearchDao.class);
//            LocationMapper locationMapper = context.getBean(LocationMapper.class);
//            SessionFactory sessionFactory = context.getBean(SessionFactory.class);
//
//            // Отримуємо існуючого користувача
//            UserEntity user;
//            try (Session session = sessionFactory.openSession()) {
//                Integer existingUserId = 1;
//              user = session.<UserEntity>get(UserEntity.class, existingUserId);
//                if (user == null) {
//                    throw new IllegalStateException("User with id " + existingUserId + " not found");
//                }
//            }
//
//            // Додаємо локацію через сервіс
//            String cityName = "Kyiv";
//            locationAddingService.addLocation(cityName, user);
//            System.out.println("✅ Локація додана для користувача: " + user.getLogin());
//
//            // Перевіряємо, чи збереглася локація
//            Optional<LocationEntity> foundEntity = searchDao.findLocationByName(cityName);
//            foundEntity.ifPresentOrElse(
//                    e -> System.out.println("🔎 Локація знайдена в БД: " + locationMapper.dto(e)),
//                    () -> System.out.println("❌ Локація не знайдена в БД")
//            );
//
//        } finally {
//            context.close();
//        }
    }
}
//public class Main {
//    public static void main(String[] args) {
//
//        AnnotationConfigApplicationContext context =
//                new AnnotationConfigApplicationContext(AppConfig.class, RestTemplateConf.class);
//
//        try {
//            LocationAddingService locationAddingService = context.getBean(LocationAddingService.class);
//            LocationSearchDao searchDao = context.getBean(LocationSearchDao.class);
//            DelistDao delistDao = context.getBean(DelistDao.class);
//            LocationMapper locationMapper = context.getBean(LocationMapper.class);
//            SessionFactory sessionFactory = context.getBean(SessionFactory.class);
//
//            // Отримуємо існуючого користувача
//            UserEntity user;
//            try (Session session = sessionFactory.openSession()) {
//                Integer existingUserId = 1;
//                user = session.get(UserEntity.class, existingUserId);
//                if (user == null) {
//                    throw new IllegalStateException("User with id " + existingUserId + " not found");
//                }
//            }
//
//            // Додаємо локацію через сервіс
//            String cityName = "Kyiv";
//            locationAddingService.addLocation(cityName, user);
//            System.out.println("✅ Локація додана для користувача: " + user.getLogin());
//
//            // Перевіряємо, чи збереглася локація
//            Optional<LocationEntity> foundEntity = searchDao.findLocationByName(cityName);
//            foundEntity.ifPresentOrElse(
//                    e -> System.out.println("🔎 Локація знайдена в БД: " + locationMapper.dto(e)),
//                    () -> System.out.println("Локація не знайдена в БД")
//            );
//
//            // Видаляємо локацію
//            delistDao.deleteLocationByName(cityName);
//            System.out.println("🗑️ Локація видалена");
//
//            // Перевіряємо, що локації більше немає
//            Optional<LocationEntity> afterDelete = searchDao.findLocationByName(cityName);
//            afterDelete.ifPresentOrElse(
//                    e -> System.out.println(" Локація все ще є в БД!"),
//                    () -> System.out.println("Локація успішно видалена з БД")
//            );
//
//        } finally {
//            context.close();
//        }
//    }
//}
//public class Main {
//    public static void main(String[] args) {
//
//        // 1. Створюємо Spring Context з двох конфігурацій
//        AnnotationConfigApplicationContext context =
//                new AnnotationConfigApplicationContext(AppConfig.class, RestTemplateConf.class);
//
//        try {
//            // 2. Отримуємо біні
//            LocationSearchService searchService = context.getBean(LocationSearchService.class);
//            LocationAddingDao addingDao = context.getBean(LocationAddingDao.class);
//            LocationSearchDao searchDao = context.getBean(LocationSearchDao.class);
//            DelistDao delistDao = context.getBean(DelistDao.class);
//            LocationMapper locationMapper = context.getBean(LocationMapper.class);
//
//            // 3. Назва міста для пошуку
//            String cityName = "Kyiv";
//
//            // 4. Шукаємо локацію через OpenWeatherMap API
//            Optional<LocationDto> locationDtoOpt = searchService.searchLocation(cityName);//
//
//            if (locationDtoOpt.isPresent()) {
//                LocationDto locationDto = locationDtoOpt.get();
//                System.out.println("🔍 Знайдено через API: " + locationDto);
//
//                // 5. Конвертуємо DTO в Entity
//                LocationEntity entity = locationMapper.entity(locationDto);
//
//                // 6. Зберігаємо в базу
//                addingDao.saveLocation(entity);
//                System.out.println("✅ Локація збережена в БД: " + entity);
//
//                // 7. Шукаємо в базі
//                Optional<LocationEntity> foundEntity = searchDao.findLocationByName(cityName);
//                foundEntity.ifPresentOrElse(
//                        e -> System.out.println("🔎 Локація знайдена в БД: " + locationMapper.dto(e)),
//                        () -> System.out.println("❌ Локація не знайдена в БД")
//                );
//
//                // 8. Видаляємо локацію (за потреби)
////                delistDao.deleteLocationByName(cityName);
////                System.out.println("🗑️ Локація видалена");
//
//            } else {
//                System.out.println("❌ Локацію не вдалося знайти через API");
//            }
//
//        } finally {
//            // 9. Закриваємо контекст
//            context.close();
//        }
//    }
//}
//    public static void main(String[] args) throws Exception {
//        // 1. Конфігурація Hibernate (AppConfig)
//        AppConfig appConfig = new AppConfig();
//        SessionFactory sessionFactory = appConfig.sessionFactory(appConfig.dataSource());
//
//        // 2. DAO + Mapper + Service
//        UserAuthorizationDao dao = new UserAuthorizationDao(sessionFactory);
//        UserMapper mapper = new UserMapperImpl(); // згенерований MapStruct
//        UserAuthorizationService service = new UserAuthorizationService(dao, mapper);
//
//        // 3. Створюємо нового юзера і хешуємо пароль
//        String login = "john";
//        String rawPassword = "12345";
//        String hashedPassword = HashPassword.hashPassword(rawPassword);
//
//        UserEntity newUser = new UserEntity();
//        newUser.setLogin(login);
//        newUser.setPassword(hashedPassword);
//
//        try (Session session = sessionFactory.openSession()) {
//            Transaction tx = session.beginTransaction();
//            session.persist(newUser);
//            tx.commit();
//        }
//
//        System.out.println("✅ Користувач збережений: " + newUser);
//
//        // 4. Тестуємо логін з правильним паролем
//        System.out.println("\n--- Тест: правильний пароль ---");
//        service.login(login, "12345").ifPresentOrElse(
//                user -> System.out.println("Успішний вхід: " + user),
//                () -> System.out.println("❌ Логін не вдався")
//        );
//
//        // 5. Тестуємо логін з неправильним паролем
//        System.out.println("\n--- Тест: неправильний пароль ---");
//        service.login(login, "wrong-pass").ifPresentOrElse(
//                user -> System.out.println("Успішний вхід: " + user),
//                () -> System.out.println("❌ Логін не вдався")
//        );
//
//        sessionFactory.close();
//
//    }
//}
