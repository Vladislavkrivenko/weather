package controller.externalApiController;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.externalAPIService.DelistService;

@Slf4j
@Controller
public class DeleteLocationController {
    @Autowired
    private DelistService delistService;

    @PostMapping("/locations/delete")
    public String deleteLocation(@RequestParam String location) {
        delistService.deletedCityByName(location);
        return "redirect:/locations";
    }
}
