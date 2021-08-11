package io.github.rodrigovedovato.letty;

import io.github.rodrigovedovato.letty.handlers.HelloWorldHandler;
import io.github.rodrigovedovato.letty.infrastructure.LoomThreadPool;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;

public class JettyServer {

    public static void main(String[] args) throws Exception {
        final int port = 8080;

        final Server server = new Server(new LoomThreadPool());

        ServerConnector connector = new ServerConnector(server);
        connector.setPort(port);

        server.setConnectors(new Connector[]{connector});
        server.setHandler(new HelloWorldHandler("Hello", "World"));
        server.start();
        server.join();
    }
}
