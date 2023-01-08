package goanvi.web.weblab3.repository;

import goanvi.web.weblab3.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);
}
