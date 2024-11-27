package co.edu.udea.salasinfo.repository;

import co.edu.udea.salasinfo.model.User;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByEmail(String username);
    boolean existsById(@NonNull String id);
    Optional<User> findByEmail(String email);
}