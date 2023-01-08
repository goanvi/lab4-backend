package goanvi.web.weblab3.security.jwt;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;

public class JwtAuthToken extends AbstractAuthenticationToken {
    private final String jwt;

    public JwtAuthToken(Collection<? extends GrantedAuthority> authorities, String jwt) {
        super(authorities);
        this.jwt = jwt;
    }
     public JwtAuthToken(String jwt){
        super(Collections.emptyList());
         this.jwt = jwt;
     }

    @Override
    public Object getCredentials() {
        return jwt;
    }

    @Override
    public Object getPrincipal() {
        return jwt;
    }


}
