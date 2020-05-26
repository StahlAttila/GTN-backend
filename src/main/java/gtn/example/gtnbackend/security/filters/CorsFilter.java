package gtn.example.gtnbackend.security.filters;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@Order(value = Integer.MIN_VALUE)
public class CorsFilter extends OncePerRequestFilter {
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    response.addHeader("Access-Control-Allow-Origin", "http://localhost:3000");
    response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
    response.addHeader("Access-Control-Max-Age", "36000");
    response.setHeader("Access-Control-Allow-Headers",
        "Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, "
            + "Access-Control-Request-Headers, Authorization, token");
    response.addHeader("Access-Control-Expose-Headers", "*");
    response.addHeader("Access-Control-Allow-Credentials", "true");
    if (request.getMethod().equalsIgnoreCase("OPTIONS")) {
      response.setStatus(200);
    } else {
      filterChain.doFilter(request, response);
    }
  }
}
