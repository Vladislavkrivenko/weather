package controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import service.LogoutService;
import util.CookieUtil;

import java.util.Arrays;
import java.util.UUID;

@Slf4j
@Controller
@RequestMapping("/logout")
public class LogoutController {
    @Autowired
    private LogoutService logoutService;

    @PostMapping
    public String logout(HttpServletResponse response, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            Arrays.stream(cookies).filter(cookie -> "SESSION_ID".equals(cookie.getName()))
                    .findFirst().ifPresent(cookie -> {
                        try {
                            UUID sessionId = UUID.fromString(cookie.getValue());
                            logoutService.invalidateSession(sessionId);
                            log.debug("Invalidated session with id {}", sessionId);
                        } catch (IllegalArgumentException e) {
                            log.warn("Invalid session id {}", e);
                        }
                        CookieUtil.removeCookie(response, "SESSION_ID");
                    });

        }
        request.getSession().invalidate();
        log.debug("Session invalidated and user logged out");
        return "redirect:/login";
    }
}
