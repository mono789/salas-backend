package co.edu.udea.salasinfo.service;

import co.edu.udea.salasinfo.dto.request.user.UserRequest;
import co.edu.udea.salasinfo.dto.response.user.UserResponse;
import co.edu.udea.salasinfo.utils.enums.RoleName;

import java.util.List;

public interface UserService {
    List<UserResponse> findAll();
    UserResponse update(String id, UserRequest userRequest);
    UserResponse updateRole(String id, RoleName roleName);
    UserResponse findById(String id);
}
