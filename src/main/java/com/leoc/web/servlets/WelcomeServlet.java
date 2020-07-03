package com.leoc.web.servlets;

import com.leoc.web.FileUtil;
import com.leoc.web.domain.User;
import com.leoc.web.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WelcomeServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("Inicializando Servlet...");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().println(FileUtil.readFromFile("welcome.html"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("fname");
        String lastName = req.getParameter("lname");
        String email = req.getParameter("email");

        UserRepository.getInstance().add(new User(name, lastName, email));
        resp.sendRedirect("room?email="+email);
    }
}
