package dao.externalAPIDao;

import entity.LocationEntity;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

@Slf4j
@Repository
public class LocationSearchDao {
    private final SessionFactory sessionFactory;

    public LocationSearchDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Optional<LocationEntity> findLocationByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM LocationEntity WHERE name = name";
            return session.createQuery(hql, LocationEntity.class)
                    .setParameter("name", name)
                    .uniqueResultOptional();
        } catch (Exception ex) {
            log.error("Error in findLocationByName", ex);
            return Optional.empty();
        }
    }
}
