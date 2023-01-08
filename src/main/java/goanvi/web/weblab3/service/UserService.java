package goanvi.web.weblab3.service;

import goanvi.web.weblab3.entity.User;
import goanvi.web.weblab3.exceptions.UserAlreadyExistsException;
import goanvi.web.weblab3.repository.UserRepository;
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
