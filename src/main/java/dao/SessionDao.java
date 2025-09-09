package dao;

import entity.SessionEntity;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Repository
public class SessionDao {
    private final SessionFactory sessionFactory;

    public SessionDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void saveSession(SessionEntity sessionEntity) {
        if (sessionEntity == null) {
            throw new IllegalArgumentException("sessionEntity cannot be null");
        }
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            try {
                session.persist(sessionEntity);
                log.info("Session has been saved successfully");
                tx.commit();
            } catch (Exception e) {
                if (tx != null) tx.rollback();
                log.error("save session exception", e);
                throw e;
            }
        }
    }

    public Optional<Integer> findUserBySession(UUID sessionId) {
        try (Session session = sessionFactory.openSession()) {
            return session.doReturningWork(connection -> {
                String sql = "SELECT user_id FROM sessions WHERE id = ?";
                try (PreparedStatement ps = connection.prepareStatement(sql)) {
                    ps.setObject(1, sessionId);
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next())
                            return Optional.of(rs.getInt("user_id"));
                        return Optional.empty();
                    }

                }
            });
        }
    }
}
