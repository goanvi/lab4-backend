package goanvi.web.weblab3.security.jwt;

import org.springframework.security.core.AuthenticationException;

public class JwtAuthenticationException extends AuthenticationException {
    public JwtAuthenticationException(String tokenIsExpired) {
        super(tokenIsExpired);
    }
}
