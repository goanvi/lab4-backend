package goanvi.web.weblab3.dto;

import lombok.Getter;

@Getter
public class TokenDTO {
    private final String token;

    public TokenDTO(String token) {
        this.token = token;
    }
}
