package service;

import dao.LogoutDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class LogoutService {
    private final LogoutDao logoutDao;

    public LogoutService(LogoutDao logoutDao) {
        this.logoutDao = logoutDao;
    }

    public void invalidateSession(UUID sessionId) {
        logoutDao.deleteSession(sessionId);
    }
}
