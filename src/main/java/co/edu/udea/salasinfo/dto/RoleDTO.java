package co.edu.udea.salasinfo.dto;

import co.edu.udea.salasinfo.utils.RoleName;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RoleDTO {
    private Integer roleId;
    private RoleName roleName;
    private List<UserDTO> users;
}
