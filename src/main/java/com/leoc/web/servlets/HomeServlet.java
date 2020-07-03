package com.leoc.web.servlets;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class HomeServlet extends HttpServlet {

    private ThymeleafLoader templates;
    private ServletContext servletContext;

    @Override
    public void init(ServletConfig config) {
        servletContext = config.getServletContext();
        templates = new ThymeleafLoader();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
        ctx.setVariable("today", new Date());

        TemplateEngine engine = templates.getTemplateEngine();
        engine.process("home", ctx, response.getWriter());
    }

}
