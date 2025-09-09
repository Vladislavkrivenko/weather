package util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import entity.LocationEntity;
import entity.SessionEntity;
import entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Slf4j
@Configuration
public class AppConfig {
    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/weather");
        config.setUsername("postgres");
        config.setPassword("pass");
        config.setMaximumPoolSize(10);
        log.debug("HikariDataSource created");
        return new HikariDataSource(config);
    }

    @Bean(initMethod = "migrate")
    public Flyway flyway(DataSource dataSource) {
        return Flyway.configure()
                .dataSource(dataSource)
                .locations("classpath:/db/migration")
                .load();
    }

    @Bean
    public SessionFactory sessionFactory(DataSource dataSource) {
        try {
            org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
            configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
            configuration.setProperty("hibernate.hbm2ddl.auto", "update");
            configuration.setProperty("hibernate.show_sql", "true");
            configuration.setProperty("hibernate.format_sql", "true");
            configuration.addAnnotatedClass(UserEntity.class);
            configuration.addAnnotatedClass(SessionEntity.class);
            configuration.addAnnotatedClass(LocationEntity.class);

            StandardServiceRegistryBuilder registryBuilder =
                    new StandardServiceRegistryBuilder()
                            .applySettings(configuration.getProperties())
                            .applySetting("hibernate.connection.datasource", dataSource);

            return configuration.buildSessionFactory(registryBuilder.build());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create SessionFactory", e);
        }
    }
}
