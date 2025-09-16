package dto;

import java.math.BigDecimal;

public record LocationDto(String nameCity, Integer UserId, BigDecimal latitude, BigDecimal longitude) {
}
