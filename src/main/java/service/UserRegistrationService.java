package service;

import dao.UserRegistrationDao;
import dto.UserDto;
import entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import mapper.UserMapper;
import org.springframework.stereotype.Service;
import util.hashPass.HashPassword;

@Service
@Slf4j
public class UserRegistrationService {
    private final UserRegistrationDao userRegistrationDao;
    private final UserMapper userMapper;

    public UserRegistrationService(UserRegistrationDao userRegistrationDao, UserMapper userMapper) {
        this.userRegistrationDao = userRegistrationDao;
        this.userMapper = userMapper;
    }

    public UserEntity saveUser(UserDto userDto) {
        String pass = HashPassword.hashPassword(userDto.password());
        UserEntity userEntity = userMapper.entity(userDto);
        userEntity.setPassword(pass);
        userRegistrationDao.registrationUser(userEntity);
        log.debug("User registered: {}", userEntity.getLogin());
        return userEntity;
    }
}

