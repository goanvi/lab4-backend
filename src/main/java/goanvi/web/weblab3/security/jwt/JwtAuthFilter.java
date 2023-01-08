package goanvi.web.weblab3.security.jwt;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Log4j2
public class JwtAuthFilter extends OncePerRequestFilter {
    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = getToken(request);
            if (!token.isEmpty()) {
                JwtAuthToken twtToken = new JwtAuthToken(token);
                JwtAuthToken authenticate = (JwtAuthToken) authenticationManager.authenticate(twtToken);
                SecurityContextHolder.getContext().setAuthentication(authenticate);
            }
        }catch (AuthenticationException e){
            log.error(e.getMessage());
        }
        filterChain.doFilter(request, response);
    }
    private String getToken(HttpServletRequest request){
        String bearer = request.getHeader("Authorization");
        if (bearer != null && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
        }
        return "";
    }
}
