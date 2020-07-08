package com.leoc.web.servlets;

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

public class QueryServlet extends HttpServlet {

    private ThymeleafLoader templates;
    private ServletContext servletContext;

    @Override
    public void init(ServletConfig config) {
        servletContext = config.getServletContext();
        templates = new ThymeleafLoader();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String message = req.getParameter("type");

        if(message!=null && message.equals("userExists")){
            validateUserExists(req,resp);
        }else if(message!=null && message.equals("chatContent")){
            resolveChatContent(req,resp);
        }

    }

    /**
     * Construye una parte del chat reflejando todos los mensajes en una estructura html
     * @param req
     * @param resp
     * @throws IOException
     */
    private void resolveChatContent(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        WebContext ctx = new WebContext(req, resp, servletContext, req.getLocale());
        ctx.setVariable("messages", MessageRepository.getInstance().all());

        TemplateEngine engine = templates.getTemplateEngine();
        engine.process("chat-content", ctx, resp.getWriter());

    }

    /**
     * query va a obedecer el formato de la url
     * query?type=userExists&email=pepito@gmail.com
     * return true si existe false si no existe
     * @param req
     * @param resp
     */
    private void validateUserExists(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email = req.getParameter("email");
        resp.getWriter().print(UserRepository.getInstance().findByEmail(email)!=null);//si no es null -> true
    }
}
