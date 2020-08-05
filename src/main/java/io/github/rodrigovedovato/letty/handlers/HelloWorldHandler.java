package io.github.rodrigovedovato.letty.handlers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

public class HelloWorldHandler extends AbstractHandler
{
    final String greeting;
    final String body;

    public HelloWorldHandler() {
        this("Hello World");
    }

    public HelloWorldHandler(String greeting) {
        this(greeting, null);
    }

    public HelloWorldHandler(String greeting, String body) {
        this.greeting = greeting;
        this.body = body;
    }

    @Override
    public void handle(String target,
                       Request baseRequest,
                       HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

        PrintWriter out = response.getWriter();

        out.println("<h1>" + greeting + "</h1>");

        if (body != null) {
            out.println(body);
        }

        baseRequest.setHandled(true);
    }
}