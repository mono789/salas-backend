package co.edu.udea.salasinfo.persistence.jpa;

import co.edu.udea.salasinfo.exceptions.EntityNotFoundException;
import co.edu.udea.salasinfo.model.User;
import co.edu.udea.salasinfo.persistence.UserDAO;
import co.edu.udea.salasinfo.repository.UserRepository;
import co.edu.udea.salasinfo.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserJPA implements UserDAO {
    private final UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(String.format(Constants.EMAIL_NOT_FOUND_MESSAGE, email)));
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findById(String customerId) {
        return userRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException(User.class.getSimpleName(), customerId));
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByEmail(username);
    }

    @Override
    public Boolean existsByCustomerId(String customerId) {
        return userRepository.existsById(customerId);
    }
}