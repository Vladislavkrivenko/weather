package dao;

import entity.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
// TODO додати логування
@Repository
public class UserAuthorizationDao {
    private final SessionFactory sessionFactory;

    public UserAuthorizationDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Optional<UserEntity> findByLogin(String login) throws SQLException {
        try (Session session = sessionFactory.openSession()) {
            return session.doReturningWork(connection -> {
                String sql = "SELECT * FROM Users WHERE login = ?";
                try (PreparedStatement ps = connection.prepareStatement(sql)) {
                    ps.setString(1, login);
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            UserEntity user = new UserEntity();
                            user.setLogin(rs.getString("login"));
                            user.setPassword(rs.getString("password"));
                            return Optional.of(user);
                        }
                    }
                }
                return Optional.empty();
            });
        }
    }
}