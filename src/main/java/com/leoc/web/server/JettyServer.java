package com.leoc.web.server;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.http.HttpServlet;
import java.util.EnumSet;

public class JettyServer {
    private Server server;
    private ServletHandler handler;

    private final Integer port;
    private final Integer maxThreads = 100;
    private final Integer minThreads = 10;
    private final Integer idleTimeout = 120;

    public JettyServer(int port) {
        this.port = port;
        handler = new ServletHandler();
    }

    public void start() throws Exception {
        QueuedThreadPool threadPool = new QueuedThreadPool(maxThreads, minThreads, idleTimeout);
        server = new Server(threadPool);
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(port);
        server.setConnectors(new Connector[] {connector});
        server.setHandler(handler);

        server.start();
        server.join();
    }

    public void registerServlet(Class<? extends HttpServlet> servlet, String path) {
        handler.addServletWithMapping(servlet, path);
    }
    public void registerFilter(Class<? extends Filter> filter, String path) {
        handler.addFilterWithMapping(filter, path, EnumSet.of(DispatcherType.REQUEST));
    }
}