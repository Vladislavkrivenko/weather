package service;

import dao.SessionDao;
import entity.SessionEntity;
import entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class SessionService {
    private UUID uuid;
    private final SessionDao sessionDao;

    public SessionService(SessionDao sessionDao) {
        this.sessionDao = sessionDao;
    }

    public UUID createSessions(UserEntity user) {
        UUID sessionId = uuid.randomUUID();
        SessionEntity sessionEntity = new SessionEntity();
        sessionEntity.setId(sessionId);
        sessionEntity.setUser(user);
        sessionEntity.setExpires_at(LocalDateTime.now().plusHours(2));
        sessionDao.saveSession(sessionEntity);
        log.debug("sessionId is created: {}", sessionId);
        return sessionId;
    }

    public Optional<Integer> findUserBySessionId(UUID sessionId) {
        if (sessionId == null) {
            log.debug("sessionId is null");
            throw new RuntimeException("sessionId is null");
        }
        return sessionDao.findUserBySession(sessionId);
    }
}
