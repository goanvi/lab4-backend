package goanvi.web.lab4_backend.dto;

import lombok.Getter;

@Getter
public class TokenDTO {
    private final String token;

    public TokenDTO(String token) {
        this.token = token;
    }
}
