package mapper;

import dto.UserDto;
import entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto dto(UserEntity user);

    UserEntity entity(UserDto userDto);
}
