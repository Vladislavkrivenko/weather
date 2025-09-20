package service.externalAPIService;

import dao.externalAPIDao.DelistDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DelistService {
    private final DelistDao delistDao;

    public DelistService(DelistDao delistDao) {
        this.delistDao = delistDao;
    }

    public void deletedCityByName(String cityName) {
        if (cityName == null || cityName.isBlank()) {
            log.debug("cityName is null or blank");
            throw new IllegalArgumentException("cityName is null or blank");
        }
        delistDao.deleteLocationByName(cityName);
    }
}
