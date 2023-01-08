package goanvi.web.lab4_backend.service;

import goanvi.web.lab4_backend.entity.User;
import goanvi.web.lab4_backend.exceptions.UserAlreadyExistsException;
import goanvi.web.lab4_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(User user) throws UserAlreadyExistsException {
        if(userRepository.existsByUsername(user.getUsername())) throw new UserAlreadyExistsException("User already exists");
        userRepository.save(user);
    }
}
