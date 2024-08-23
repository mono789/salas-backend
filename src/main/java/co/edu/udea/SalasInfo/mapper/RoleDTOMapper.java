package co.edu.udea.SalasInfo.mapper;

import co.edu.udea.SalasInfo.dto.RoleDTO;
import co.edu.udea.SalasInfo.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleDTOMapper {
    RoleDTO toDTO(Role role);
    Role toEntity(RoleDTO roleDTO);
    List<RoleDTO> toDTOs(List<Role> roles);
    List<Role> toEntities(List<RoleDTO> roleDTOs);
}
