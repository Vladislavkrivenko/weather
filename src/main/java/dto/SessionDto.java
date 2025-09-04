package dto;

import java.time.LocalDateTime;

public record SessionDto(int userId, LocalDateTime startTime, LocalDateTime endTime) {

}
