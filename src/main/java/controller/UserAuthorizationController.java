package controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.SessionService;
import service.UserAuthorizationService;
import util.CookieUtil;

import java.util.UUID;

@Slf4j

@Controller
@RequestMapping("/auth")
public class UserAuthorizationController {
    @Autowired
    private UserAuthorizationService userAuthorizationService;
    @Autowired
    private SessionService sessionService;
    @Autowired
    private UserMapper userMapper;


    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String login,
                        @RequestParam String password,
                        Model model,
                        HttpServletResponse response) {
        try {
            return userAuthorizationService.loginUser(login, password)
                    .map(user -> {
                        log.debug("User {} successfully logged in", user.login());
                        UUID sessionId = sessionService.createSessions(userMapper.entity(user));
                        CookieUtil.addCookie(response, "SESSION_ID", sessionId.toString(), 1800);

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

