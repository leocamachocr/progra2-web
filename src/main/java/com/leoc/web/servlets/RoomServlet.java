package com.leoc.web.servlets;

import com.leoc.web.domain.Message;
import com.leoc.web.domain.User;
import com.leoc.web.repository.MessageRepository;
import com.leoc.web.repository.UserRepository;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RoomServlet extends HttpServlet {


    private ThymeleafLoader templates;
    private ServletContext servletContext;

    @Override
    public void init(ServletConfig config) {
        servletContext = config.getServletContext();
        templates = new ThymeleafLoader();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
        ctx.setVariable("users", UserRepository.getInstance().all());
        ctx.setVariable("messages", MessageRepository.getInstance().all());

        TemplateEngine engine = templates.getTemplateEngine();
        engine.process("room", ctx, response.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String message = req.getParameter("message");
        String email = req.getParameter("email");
        User user= UserRepository.getInstance().findByEmail(email);
        MessageRepository.getInstance().add(new Message(message,user));
        resp.sendRedirect("room?email="+email);
    }
}
