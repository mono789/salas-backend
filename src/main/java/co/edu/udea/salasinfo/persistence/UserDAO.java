package co.edu.udea.salasinfo.persistence;

import co.edu.udea.salasinfo.model.User;

import java.util.List;

public interface UserDAO{
    List<User> findAll();
    User findByEmail(String username);
    User save(User user);
    User findById(String customerId);
    Boolean existsByUsername(String username);
    Boolean existsByCustomerId(String customerId);
}