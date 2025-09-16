package service;

import dao.UserAuthorizationDao;
import dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import mapper.UserMapper;
import org.springframework.stereotype.Service;
import util.hashPass.HashPassword;

import java.sql.SQLException;
import java.util.Optional;

@Slf4j
@Service
public class UserAuthorizationService {
    private final UserAuthorizationDao userAuthorizationDao;
    private final UserMapper userMapper;

    public UserAuthorizationService(UserAuthorizationDao userAuthorizationDao, UserMapper userMapper) {
        this.userAuthorizationDao = userAuthorizationDao;
        this.userMapper = userMapper;
    }

    public Optional<UserDto> login(String login, String rawPassword) throws SQLException {
        if (login == null || login.isEmpty() || rawPassword == null || rawPassword.isEmpty()) {
            log.debug("login or rawPassword is null");
            throw new IllegalArgumentException("login or rawPassword is null");
        }
        return userAuthorizationDao.findByLogin(login)
                .filter(user -> HashPassword.checkPassword(rawPassword, user.getPassword()))
                .map(userMapper::dto);
    }
}
