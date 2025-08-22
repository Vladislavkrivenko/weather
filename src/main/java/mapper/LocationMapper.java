package mapper;

import dto.LocationDto;
import entity.LocationEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocationMapper {
    LocationEntity entity (LocationDto locationDto);
    LocationDto dto (LocationEntity locationEntity);
}
