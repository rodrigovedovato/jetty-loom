package io.github.rodrigovedovato.letty.handlers;

import io.github.rodrigovedovato.letty.infrastructure.DelayObject;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.*;

public class BlockingHandler extends AbstractHandler {
    private BlockingQueue<DelayObject> queue = null;
    private final ExecutorService executorService;

    public BlockingHandler(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (queue == null) {
            queue = new DelayQueue<>();

            executorService.submit(() -> {
                for (int i = 0; i < 1000 * 60 * 5; i++) {
                    queue.add(new DelayObject(Integer.toString(i), 2000));
                }
            });
        }

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);

        PrintWriter out = response.getWriter();

        try {
            out.println(queue.take().toString());
        } catch (InterruptedException ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

        baseRequest.setHandled(true);
    }
}
