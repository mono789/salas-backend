package co.edu.udea.salasinfo.mapper;

import co.edu.udea.salasinfo.dto.UserDTO;
import co.edu.udea.salasinfo.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserDTOMapper {
    UserDTO toDTO(User user);
    User toEntity(UserDTO userDTO);
    List<UserDTO> toDTOs(List<User> users);
    List<User> toEntities(List<UserDTO> userDTOs);
}
