package co.edu.udea.salasinfo.service.impl;

import co.edu.udea.salasinfo.dto.request.user.UserRequest;
import co.edu.udea.salasinfo.dto.response.user.UserResponse;
import co.edu.udea.salasinfo.mapper.request.UserRequestMapper;
import co.edu.udea.salasinfo.mapper.response.UserResponseMapper;
import co.edu.udea.salasinfo.model.Role;
import co.edu.udea.salasinfo.model.User;
import co.edu.udea.salasinfo.persistence.RoleDAO;
import co.edu.udea.salasinfo.persistence.UserDAO;
import co.edu.udea.salasinfo.service.UserService;
import co.edu.udea.salasinfo.utils.enums.RoleName;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;
    private final RoleDAO roleDAO;
    private final UserResponseMapper userResponseMapper;
    private final UserRequestMapper userRequestMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserResponse> findAll() {
        return userResponseMapper.toResponses(userDAO.findAll());
    }

    @Override
    @Transactional
    public UserResponse update(String id, UserRequest userRequest) {
        User user = userDAO.findById(id);
        User entity = userRequestMapper.toEntity(userRequest);
        if(entity.getFirstname() != null) user.setFirstname(entity.getFirstname());
        if(entity.getLastname() != null) user.setLastname(entity.getLastname());
        if(entity.getPassword() != null) user.setPassword(passwordEncoder.encode(entity.getPassword()));
        user = userDAO.save(user);
        return userResponseMapper.toResponse(user);
    }

    @Override
    @Transactional
    public UserResponse updateRole(String id, RoleName roleName) {
        User user = userDAO.findById(id);
        Role role = roleDAO.findByRoleName(roleName);
        user.setRole(role);
        user = userDAO.save(user);
        return userResponseMapper.toResponse(user);
    }

    @Override
    public UserResponse findById(String id) {
        return userResponseMapper.toResponse(
                userDAO.findById(id)
        );
    }
}
