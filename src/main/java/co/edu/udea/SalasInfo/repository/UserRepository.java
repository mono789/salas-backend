package co.edu.udea.SalasInfo.repository;

import co.edu.udea.SalasInfo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByEmail(String username);
    Boolean existsByCustomerId(Long customerId);
    Optional<User> findByEmail(String email);
}