package dao.externalAPIDao;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Repository
public class DelistDao {
    private final SessionFactory sessionFactory;

    public DelistDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void deleteLocationByName(BigDecimal latitude, BigDecimal longitude) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            String hql =
                    "DELETE FROM LocationEntity WHERE latitude = :latitude AND longitude = longitude";
            session.createQuery(hql)
                    .setParameter("latitude", latitude)
                    .setParameter("longitude", longitude)
                    .executeUpdate();
            tx.commit();
            log.info("Deleted location with latitude={} and longitude{}", latitude, longitude);
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                log.error("RollBack of location with latitude {} and longitude{}", latitude, longitude);
            }
        }
    }

}
