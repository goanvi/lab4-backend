package goanvi.web.lab4_backend.dto;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class TokenDTO implements Serializable {
    private final String token;

    public TokenDTO(String token) {
        this.token = token;
    }
}
