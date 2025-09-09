package dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record SessionDto(UUID id, int user_id, LocalDateTime expires_at) {

}
