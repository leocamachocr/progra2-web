package com.leoc.web.server;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;

import javax.servlet.http.HttpServlet;

public class JettyServer {
    private Server server;
    private ServletHandler handler;

    private final Integer port;

    public JettyServer(int port) {
        this.port = port;
        handler = new ServletHandler();
    }

    public void start() throws Exception {
        server = new Server(port);
        server.setHandler(handler);
//        ServerConnector connector = new ServerConnector(server);
//        connector.setPort(port);
//        server.setConnectors(new Connector[]{connector});
        server.start();
        server.join();
    }

    public void registerServlet(Class<? extends HttpServlet> servlet, String path) {
        handler.addServletWithMapping(servlet, path);
    }
}