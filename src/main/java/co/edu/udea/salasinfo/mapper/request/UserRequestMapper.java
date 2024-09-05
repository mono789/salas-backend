package co.edu.udea.salasinfo.mapper.request;

import co.edu.udea.salasinfo.dto.response.UserResponse;
import co.edu.udea.salasinfo.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserRequestMapper {
    UserResponse toDTO(User user);
    User toEntity(UserResponse userResponse);
    List<UserResponse> toDTOs(List<User> users);
    List<User> toEntities(List<UserResponse> userResponses);
}
