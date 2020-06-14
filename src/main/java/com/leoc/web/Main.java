package com.leoc.web;

import com.leoc.web.server.JettyServer;
import com.leoc.web.servlets.HomeServlet;

public class Main {

    public static void main(String[] args) throws Exception {
        String webPort = System.getenv("PORT");
        if (webPort == null || webPort.isEmpty()) {
            webPort = "8080";
        }

        JettyServer server = new JettyServer(Integer.parseInt(webPort));
        server.registerServlet(HomeServlet.class, "/home");
        server.start();
    }
}
