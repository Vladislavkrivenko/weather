package mapper;

import dto.LocationDto;
import entity.LocationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LocationMapper {

    @Mapping(target = "user", ignore = true)
        // не підставляємо юзера автоматично
    LocationEntity entity(LocationDto locationDto);

    @Mapping(source = "user.id", target = "userId")
    LocationDto dto(LocationEntity locationEntity);
}
