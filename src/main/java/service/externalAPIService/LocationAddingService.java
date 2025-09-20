package service.externalAPIService;

import dao.externalAPIDao.LocationAddingDao;
import dao.externalAPIDao.LocationSearchDao;
import dto.LocationDto;
import entity.LocationEntity;
import entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Slf4j
@Service
public class LocationAddingService {
    private final LocationAddingDao locationAddingDao;
    private final LocationSearchService locationSearchService;

    public LocationAddingService(LocationAddingDao locationAddingDao, LocationSearchService locationSearchService) {
        this.locationAddingDao = locationAddingDao;
        this.locationSearchService = locationSearchService;
    }

    public void addLocation(String name, UserEntity user) {
        if (name == null || name.isEmpty() || user == null) {
            log.error("Invalid Input");
            throw new IllegalArgumentException("Invalid Input");
        }
        Optional<LocationDto> locationDtoOpt = locationSearchService.searchLocation(name);
        if (locationDtoOpt.isEmpty()) {
            log.error("Location not found for city: {}", name);
            throw new IllegalArgumentException("Location not found for city: " + name);
        }
        LocationDto locationDto = locationDtoOpt.get();
        LocationEntity locationEntity = new LocationEntity();
        locationEntity.setName(locationDto.name());
        locationEntity.setLatitude(locationDto.latitude());
        locationEntity.setLongitude(locationDto.longitude());
        locationEntity.setUser(user);
        locationAddingDao.saveLocation(locationEntity);
        log.info("Location saved from external API: {}", locationEntity);
    }

}
