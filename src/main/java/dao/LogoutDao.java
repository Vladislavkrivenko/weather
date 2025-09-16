package dao;

import entity.SessionEntity;
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
            String hql = "DELETE FROM SessionEntity WHERE id = :id";
            session.createQuery(hql, SessionEntity.class)
                    .setParameter("id", sessionId)
                    .executeUpdate();
            tx.commit();
            log.debug("Deleted session with id {}", sessionId);
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                log.error("Rollback of session with id {}", sessionId, e);
            }
            throw e;
        }
    }
}
