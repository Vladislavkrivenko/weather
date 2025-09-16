package dao.externalAPIDao;

import entity.LocationEntity;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class LocationAddingDao {
    private final SessionFactory sessionFactory;

    public LocationAddingDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void saveLocation(LocationEntity location) {
        if (location == null) {
            log.error("location is null");
            throw new IllegalArgumentException("location is null");
        }
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
                session.persist(location);
                tx.commit();
            log.info("Location added successfully: {}", location);
            } catch (Exception e) {
                log.error("save Location exception", e);
                if (tx != null) tx.rollback();
                throw e;
        }
    }
}
