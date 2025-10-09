package dao;

import entity.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserDao {
    private final SessionFactory sessionFactory;

    public Optional<UserEntity> findById(Integer id) {
        if(id == null){
            return Optional.empty();
        }
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(UserEntity.class, id));
        }catch (Exception e){
            log.error("Error fetching user with id {}", id, e);
            return Optional.empty();
        }
    }
}
