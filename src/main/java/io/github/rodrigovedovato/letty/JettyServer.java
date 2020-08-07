package io.github.rodrigovedovato.letty;

import io.github.rodrigovedovato.letty.handlers.HelloWorldHandler;
import io.github.rodrigovedovato.letty.infrastructure.LoomThreadPool;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;

public class JettyServer {
    public static Server createLoomBasedServer(int port) {
        var server = new Server(new LoomThreadPool());

        ServerConnector connector = new ServerConnector(server);
        connector.setPort(port);

        server.setConnectors(new Connector[]{connector});
        server.setHandler(new HelloWorldHandler());

        return server;
    }

    public static Server createThreadPoolBasedServer(int port) {
        var server = new Server(port);
        server.setHandler(new HelloWorldHandler());

        return server;
    }

    public static void main(String[] args) throws Exception {
        var server = createLoomBasedServer(8080);
        //var server = createThreadPoolBasedServer(8080);

        server.start();
        server.join();
    }
}
