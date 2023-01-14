package goanvi.web.lab4_backend.dto;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class RegisterDTO implements Serializable {
    String login;
    String password;
}
