package io.github.rodrigovedovato.letty.handlers;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

public class FactorialHandler extends AbstractHandler {
    public FactorialHandler() {

    }

    private final Random random = new Random();

    private BigInteger getFactorial(long number) {
        var bigNum = BigInteger.valueOf(number);
        var factorial = BigInteger.ONE;

        BigInteger b = bigNum;
        while (b.compareTo(BigInteger.ZERO) > 0) {
            factorial = b.multiply(factorial);
            b = b.subtract(BigInteger.ONE);
        }

        return factorial;
    }

    @Override
    public void handle(String target,
                       Request baseRequest,
                       HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        response.setContentType("application/json");

        PrintWriter out = response.getWriter();

        int low = 10000;
        int high = 30000;

        int result = random.nextInt(high-low) + low;

        try {
            var factorial = getFactorial(Integer.toUnsignedLong(result));
            out.print(factorial.toString());

            response.setStatus(HttpServletResponse.SC_OK);
            baseRequest.setHandled(true);
        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            baseRequest.setHandled(false);
        }
    }
}