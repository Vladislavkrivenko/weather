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
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            try {
                session.persist(sessionEntity);
                tx.commit();
                log.info("Session has been saved successfully");
            } catch (Exception e) {
                if (tx != null) tx.rollback();
                log.error("save session exception", e);
                throw e;
            }
        }
    }

    public Optional<Integer> findUserBySession(UUID sessionId) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "SELECT s.user.id FROM SessionEntity s WHERE s.id = :id";
            return session.createQuery(hql, Integer.class)
                    .setParameter("id", sessionId)
                    .uniqueResultOptional();
        } catch (Exception e) {
            log.error("findUserBySession exception", e);
            return Optional.empty();
        }
    }
}

