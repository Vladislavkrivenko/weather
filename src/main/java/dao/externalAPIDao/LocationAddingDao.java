package dao.externalAPIDao;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class LocationAddingDao {
    private final SessionFactory sessionFactory;

    public LocationAddingDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


}
