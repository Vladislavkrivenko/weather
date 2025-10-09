package controller.externalApiController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.externalAPIService.LocationSearchService;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LocationSearchController {

    @Autowired
    private LocationSearchService locationSearchService;

    @GetMapping("/weather")
    public String locationSearch(@RequestParam("city") String city, Model model) {
        if (city != null && !city.isBlank()) {
            locationSearchService.getWeather(city).ifPresentOrElse(
                    weather -> model.addAttribute("weather", weather),
                    () -> model.addAttribute("error", "Invalid city")
            );
        }
        return "weather";
    }
}
