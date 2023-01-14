package goanvi.web.lab4_backend.controller;

import goanvi.web.lab4_backend.dto.RegisterDTO;
import goanvi.web.lab4_backend.dto.TokenDTO;
import goanvi.web.lab4_backend.entity.User;
import goanvi.web.lab4_backend.exceptions.UserAlreadyExistsException;
import goanvi.web.lab4_backend.security.CustomUserDetails;
import goanvi.web.lab4_backend.security.jwt.JwtUtils;
import goanvi.web.lab4_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/login")
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
}
