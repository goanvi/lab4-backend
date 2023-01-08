package goanvi.web.weblab3.security;

import goanvi.web.weblab3.entity.User;
import goanvi.web.weblab3.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

//TODO обработать ошибки
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()){
            return CustomUserDetails.build(user.get());

        }else {
            throw new UsernameNotFoundException("User " + username + " not found");
        }
    }

    public CustomUserDetails loadUserByUserId(long id){
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            return CustomUserDetails.build(user.get());

        }else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}

