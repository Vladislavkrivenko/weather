package dto;

import java.math.BigDecimal;

public record LocationDto(String nameCity, int UserId, BigDecimal latitude, BigDecimal longitude) {
}
