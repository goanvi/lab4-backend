package goanvi.web.weblab3.controller;

import goanvi.web.weblab3.dto.RegisterDTO;
import goanvi.web.weblab3.dto.TokenDTO;
import goanvi.web.weblab3.entity.User;
import goanvi.web.weblab3.exceptions.UserAlreadyExistsException;
import goanvi.web.weblab3.security.CustomUserDetails;
import goanvi.web.weblab3.security.jwt.JwtUtils;
import goanvi.web.weblab3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/security")
public class SecurityController {
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    private final UserService userService;


    @Autowired
    public SecurityController(JwtUtils jwtUtils, PasswordEncoder passwordEncoder, UserService userService) {
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(Authentication authentication){
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String accessToken = jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(new TokenDTO(accessToken));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDTO registerDTO)throws UserAlreadyExistsException {
            User user = new User(registerDTO.getLogin(),
                    passwordEncoder.encode(registerDTO.getPassword()));
        userService.addUser(user);
        CustomUserDetails userDetails = CustomUserDetails.build(user);
        String accessToken = jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(new TokenDTO(accessToken));
    }
    //TODO: add logout method
}
