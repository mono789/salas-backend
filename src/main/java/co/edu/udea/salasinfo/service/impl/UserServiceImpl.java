package co.edu.udea.salasinfo.service.impl;

import co.edu.udea.salasinfo.dto.request.UserRequest;
import co.edu.udea.salasinfo.dto.response.user.UserResponse;
import co.edu.udea.salasinfo.persistence.RoleDAO;
import co.edu.udea.salasinfo.persistence.UserDAO;
import co.edu.udea.salasinfo.service.UserService;
import co.edu.udea.salasinfo.utils.enums.RoleName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;
    private final RoleDAO roleDAO;

    @Override
    public List<UserResponse> getAllUsers() {
        return List.of();
    }

    @Override
    public UserResponse update(UserRequest userRequest) {
        return null;
    }

    @Override
    public UserResponse updateRole(String id, RoleName roleName) {
        return null;
    }
}
