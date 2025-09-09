package dao;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Slf4j
@Repository
public class LogoutDao {
    private final SessionFactory sessionFactory;

    public LogoutDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void deleteSession(UUID sessionId) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            String sql = "DELETE FROM SessionEntity WHERE id = ?";
            session.createQuery(sql)
                    .setParameter("id", sessionId)
                    .executeUpdate();
            tx.commit();
            log.debug("Deleted session with id {}", sessionId);
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                log.error("Rollback of session with id {}", sessionId);
            }
            throw e;
        }
    }
}
