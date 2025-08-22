package entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class SessionEntity {
   private int UserId;
   private LocalDateTime startTime;
}
