package io.github.rodrigovedovato.letty;

import io.github.rodrigovedovato.letty.handlers.BlockingHandler;
import io.github.rodrigovedovato.letty.handlers.FactorialHandler;
import io.github.rodrigovedovato.letty.infrastructure.LoomThreadPool;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JettyServer {
    private static final ExecutorService executorService = Executors.newVirtualThreadExecutor();

    public static Server createLoomBasedServer(int port) {
        var server = new Server(new LoomThreadPool(executorService));

        ServerConnector connector = new ServerConnector(server);
        connector.setPort(port);

        server.setConnectors(new Connector[]{connector});
        server.setHandler(new BlockingHandler(executorService));

        return server;
    }

    public static Server createThreadPoolBasedServer(int port) {
        var server = new Server(port);
        server.setHandler(new FactorialHandler());

        return server;
    }

    public static void main(String[] args) throws Exception {
        var port = 8080;
        Server server = null;
        var serverMode = System.getenv("SERVER_MODE");

        if ("loom".equalsIgnoreCase(serverMode)) {
            server = createLoomBasedServer(port);
        } else if ("standard".equalsIgnoreCase(serverMode)) {
            server = createThreadPoolBasedServer(port);
        } else {
            throw new RuntimeException();
        }

        server.start();
        server.join();
    }
}
