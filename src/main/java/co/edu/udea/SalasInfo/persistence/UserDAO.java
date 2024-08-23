package co.edu.udea.SalasInfo.persistence;

import co.edu.udea.SalasInfo.model.User;

public interface UserDAO{
    User findByEmail(String username);
    User save(User user);
    User findById(Long customerId);
    Boolean existsByUsername(String username);
    Boolean existsByCustomerId(Long customerId);
}