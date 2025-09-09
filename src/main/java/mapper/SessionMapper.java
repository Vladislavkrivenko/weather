package mapper;

import dto.SessionDto;
import entity.SessionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SessionMapper {
    SessionEntity entity(SessionDto dto);

    SessionDto dto(SessionEntity entity);
}
