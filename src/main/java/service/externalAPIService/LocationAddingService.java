package service.externalAPIService;

import dao.UserDao;
import dao.externalAPIDao.LocationAddingDao;
import dto.LocationDto;
import entity.LocationEntity;
import entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LocationAddingService {
    private final LocationAddingDao locationAddingDao;
    private final LocationSearchService locationSearchService;
    private final UserDao userDao;

    public LocationAddingService(LocationAddingDao locationAddingDao, LocationSearchService locationSearchService, UserDao userDao) {
        this.locationAddingDao = locationAddingDao;
        this.locationSearchService = locationSearchService;
        this.userDao = userDao;
    }

    public void addLocation(String name, Integer userId) {
        if (name == null || name.isEmpty() || userId == null) {
            log.error("Invalid Input");
            throw new IllegalArgumentException("Invalid Input");
        }

        LocationDto locationDto = locationSearchService.searchLocation(name)
                .orElseThrow(() -> new IllegalArgumentException("City not found: " + name));

        UserEntity user = userDao.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found for id: " + userId));

        LocationEntity locationEntity = new LocationEntity();
        locationEntity.setName(locationDto.name());
        locationEntity.setLatitude(locationDto.latitude());
        locationEntity.setLongitude(locationDto.longitude());
        locationEntity.setUser(user);
        locationAddingDao.saveLocation(locationEntity);
        log.info("Location saved from external API: {}", locationEntity);
    }

}
