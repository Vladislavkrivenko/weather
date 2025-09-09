package controller;

import dto.SessionDto;
import entity.SessionEntity;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.SessionService;

import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

@WebServlet("/cookies")
public class CookieServlet extends HttpServlet {
    public static final String COOKIE_NAME = "user_id";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var cookies = req.getCookies();
        UUID uuid = UUID.randomUUID();
        if (cookies == null || Arrays.stream(cookies)
                .filter(cookie -> COOKIE_NAME.equals(cookie.getName()))
                .findFirst()
                .isEmpty()) {
            var cookie = new Cookie(COOKIE_NAME, uuid.toString());
            cookie.setMaxAge(120 * 60);
            resp.addCookie(cookie);
        }
        resp.setContentType("text/html;charset=utf-8");
    }
}
