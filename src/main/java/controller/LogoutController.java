package controller;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import service.LogoutService;

import java.util.UUID;

@Slf4j
@Controller
@RequestMapping("/logout")
public class LogoutController {
    private final LogoutService logoutService;

    public LogoutController(LogoutService logoutService) {
        this.logoutService = logoutService;
    }

    @PostMapping("logout")
    public String logout(HttpSession session) {
        UUID sessionId = (UUID) session.getAttribute("sessionId");
        if (sessionId != null) {
            logoutService.invalidateSession(sessionId);
            log.debug("Invalidated session with id {}", sessionId);
        }
        session.invalidate();
        return "redirect:/login";
    }
}
