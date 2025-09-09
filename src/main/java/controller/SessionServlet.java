package controller;

import dto.SessionDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

import static java.time.LocalDateTime.now;

@WebServlet("/session")
public class SessionServlet extends HttpServlet {
    public static final String USER = "user";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var session = req.getSession();
        UUID uuid = UUID.randomUUID();
        var user = (SessionDto) session.getAttribute(USER);
        if (user == null) {
            user = SessionDto.builder()
                    .id(uuid)
                    .userId(1)
                    .expires_at(now())
                    .build();
            session.setAttribute(USER, user);
        }
        System.out.println(session.isNew());
    }
}
