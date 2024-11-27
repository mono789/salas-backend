package co.edu.udea.salasinfo.mapper.response;

import co.edu.udea.salasinfo.dto.response.user.UserResponse;
import co.edu.udea.salasinfo.model.User;
import co.edu.udea.salasinfo.utils.Generated;
import org.mapstruct.AnnotateWith;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@AnnotateWith(Generated.class)
@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserResponseMapper {
    UserResponse toResponse(User user);

    List<UserResponse> toResponses(List<User> users);
}
