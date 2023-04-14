package site.sammati.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.sammati.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUserName(String username);

}
