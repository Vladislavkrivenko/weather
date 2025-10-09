package controller.externalApiController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import service.externalAPIService.LocationAddingService;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("locations")
public class AddLocationController {
    @Autowired
    private LocationAddingService locationAddingService;

    public String addLocation(@RequestParam("name") String locationName,
                              @SessionAttribute("userId") Integer userId,
                              RedirectAttributes redirectAttributes) {
        try {
            locationAddingService.addLocation(locationName, userId);
            redirectAttributes.addFlashAttribute("message", "Successfully added location");
        } catch (IllegalArgumentException e) {
            log.warn("Failed to add location: {}", e.getMessage());
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/locations";
    }
}
