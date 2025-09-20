package service.externalAPIService;

import dto.LocationDto;
import entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class LocationSearchService {
    private final RestTemplate restTemplate;
    private final String apiKey = "eded7fcbb435c8fc18fc5c0c886c9a38";
    private final String apiUrl = "https://api.openweathermap.org/data/2.5/weather";

    public LocationSearchService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Optional<LocationDto> searchLocation(String location) {
        if (location == null || location.isBlank()) {
            log.debug("Location is null or blank");
            return Optional.empty();
        }
        try {
            String url = String.format("%s?q=%s&appid=%s", apiUrl, location, apiKey);
            ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                Map<String, Object> body = response.getBody();
                String name = (String) body.get("name");
                Map<String, Object> coordinates = (Map<String, Object>) body.get("coord");
                BigDecimal longitude = new BigDecimal(coordinates.get("lon").toString());
                BigDecimal latitude = new BigDecimal(coordinates.get("lat").toString());

                return Optional.of(new LocationDto(name, null, latitude, longitude));//
            }
        } catch (Exception e) {
            log.error("Error fetching location from external API: {}", e.getMessage(), e);
        }
        return Optional.empty();
    }
}
