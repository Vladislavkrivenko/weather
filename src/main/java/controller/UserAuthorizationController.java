package controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.UserAuthorizationService;

@Slf4j
@Controller
@RequestMapping("/auth")
public class UserAuthorizationController {
    private final UserAuthorizationService userAuthorizationService;

    public UserAuthorizationController(UserAuthorizationService userAuthorizationService) {
        this.userAuthorizationService = userAuthorizationService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String login,
                        @RequestParam String password,
                        Model model) {
        try {
            return userAuthorizationService.loginUser(login, password)
                    .map(user -> {
                        log.info("User {} successfully logged in", user.login());
                        model.addAttribute("user", user);
                        return "redirect:/home";
                    })
                    .orElseGet(() -> {
                        log.warn("User {} could not be logged in", login);
                        model.addAttribute("error", "User not logged in");
                        return "login";
                    });
        } catch (Exception e) {
            log.error("Error during login", e);
            model.addAttribute("error", "Error during login");
            return "login";
        }
    }
}

