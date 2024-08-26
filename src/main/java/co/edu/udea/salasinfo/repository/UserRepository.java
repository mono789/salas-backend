package co.edu.udea.salasinfo.repository;

import co.edu.udea.salasinfo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByEmail(String username);
    Boolean existsByUserId(Long customerId);
    Optional<User> findByEmail(String email);
}