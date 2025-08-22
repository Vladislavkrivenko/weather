package entity;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LocationEntity {
    private String nameCity;
    private int UserId;
    private BigDecimal latitude;
    private BigDecimal longitude;
}
