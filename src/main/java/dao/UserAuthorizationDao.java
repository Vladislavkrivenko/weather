package dao;

import entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Slf4j
@Repository
public class UserAuthorizationDao {
    private final SessionFactory sessionFactory;

    public UserAuthorizationDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Optional<UserEntity> findByLogin(String login) throws SQLException {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM UserEntity u WHERE u.login = :login";
            return session.createQuery(hql, UserEntity.class)
                    .setParameter("login", login)
                    .uniqueResultOptional();
        } catch (Exception e) {
            log.error("Error finding user by login={}", login, e);
            return Optional.empty();
        }
    }
}