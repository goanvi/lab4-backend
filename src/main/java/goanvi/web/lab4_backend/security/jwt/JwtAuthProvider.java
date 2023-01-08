package goanvi.web.lab4_backend.security.jwt;

import goanvi.web.lab4_backend.security.CustomUserDetails;
import goanvi.web.lab4_backend.security.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

@RequiredArgsConstructor
@Log4j2
public class JwtAuthProvider implements AuthenticationProvider {
    @Autowired
    private final JwtUtils jwtUtils;
    private final CustomUserDetailsService userDetailsService;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try{
        JwtAuthToken jwtToken = (JwtAuthToken) authentication;
        String token = (String) jwtToken.getCredentials();
        if(jwtUtils.tokenIsValid(token)){
            long userId = Long.parseLong(jwtUtils.extractUserId(token));
            CustomUserDetails user = userDetailsService.loadUserByUserId(userId);
            jwtToken = new JwtAuthToken(user.getAuthorities(), token);
            jwtToken.setDetails(user);
            jwtToken.setAuthenticated(true);
            return jwtToken;
        }else{
            throw new JwtAuthenticationException("Token is expired");
        }
        }catch (Exception ex){
            throw new JwtAuthenticationException("Invalid access token");
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthToken.class.isAssignableFrom(authentication);
    }
}
