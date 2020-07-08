package com.leoc.web;

import com.leoc.web.filters.LogFilter;
import com.leoc.web.server.JettyServer;
import com.leoc.web.servlets.*;

public class Main {

    public static void main(String[] args) throws Exception {
        String webPort = System.getenv("PORT");
        if (webPort == null || webPort.isEmpty()) {
            webPort = "8080";
        }

        JettyServer server = new JettyServer(Integer.parseInt(webPort));
        server.registerServlet(HomeServlet.class, "/home");
        server.registerServlet(WelcomeServlet.class, "/welcome");
        server.registerServlet(ResourcesServlet.class, "/resource");
        server.registerServlet(RoomServlet.class, "/room");
        server.registerServlet(QueryServlet.class, "/query");
        server.registerFilter(LogFilter.class, "/home");
        server.start();
    }
}
