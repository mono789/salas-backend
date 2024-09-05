package co.edu.udea.salasinfo.mapper.response;

import co.edu.udea.salasinfo.dto.response.UserResponse;
import co.edu.udea.salasinfo.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserResponseMapper {
    UserResponse toResponse(User user);
    List<UserResponse> toResponses(List<User> users);
}
