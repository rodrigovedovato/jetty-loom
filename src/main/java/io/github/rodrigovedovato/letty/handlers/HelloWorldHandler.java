package io.github.rodrigovedovato.letty.handlers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class HelloWorldHandler extends AbstractHandler {
    final String greeting;
    final String body;

    public HelloWorldHandler(String greeting, String body) {
        this.greeting = greeting;
        this.body = body;
    }

    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

        PrintWriter responseWriter = response.getWriter();
        responseWriter.println("<h1>" + greeting + "</h1>");
        responseWriter.println(body);

        final int low = 500;
        final int high = 1000;
        int result = new Random().nextInt(high - low) + low;

        try {
            Thread.sleep(Integer.toUnsignedLong(result));
            baseRequest.setHandled(true);
        } catch (InterruptedException ex) {
            baseRequest.setHandled(false);
        }
    }
}