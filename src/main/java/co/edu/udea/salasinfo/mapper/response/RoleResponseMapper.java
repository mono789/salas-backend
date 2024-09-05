package co.edu.udea.salasinfo.mapper.response;

import co.edu.udea.salasinfo.dto.response.RoleResponse;
import co.edu.udea.salasinfo.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleResponseMapper {
    RoleResponse toResponse(Role role);
    List<RoleResponse> toResponses(List<Role> roles);
}
