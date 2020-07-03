package com.leoc.web.servlets;

import com.leoc.web.FileUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResourcesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        FileUtil.readResource(req.getParameter("name"), resp.getOutputStream());

    }
}
