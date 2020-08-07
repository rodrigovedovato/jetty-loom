package io.github.rodrigovedovato.letty.handlers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

public class HelloWorldHandler extends AbstractHandler
{
    final String greeting;
    final String body;

    private Random random = new Random();

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

        int low = 500;
        int high = 1000;
        int result = random.nextInt(high-low) + low;

        try {
            Thread.sleep(Integer.toUnsignedLong(result));
            baseRequest.setHandled(true);
        } catch (InterruptedException ex){
            baseRequest.setHandled(false);
        }
    }
}