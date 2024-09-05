package co.edu.udea.salasinfo.persistence;

import co.edu.udea.salasinfo.model.User;

public interface UserDAO{
    User findByEmail(String username);
    User save(User user);
    User findById(String customerId);
    Boolean existsByUsername(String username);
    Boolean existsByCustomerId(String customerId);
}