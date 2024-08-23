package co.edu.udea.SalasInfo.persistence.jpa;

import co.edu.udea.SalasInfo.exceptions.EntityNotFoundException;
import co.edu.udea.SalasInfo.model.User;
import co.edu.udea.SalasInfo.persistence.UserDAO;
import co.edu.udea.SalasInfo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserJPA implements UserDAO {
    private final UserRepository userRepository;

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User with email " + email + " not found"));
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findById(Long customerId) {
        return userRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException(User.class.getSimpleName(), Math.toIntExact(customerId)));
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByEmail(username);
    }

    @Override
    public Boolean existsByCustomerId(Long customerId) {
        return userRepository.existsByCustomerId(customerId);
    }
}