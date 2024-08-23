package co.edu.udea.SalasInfo.dto;

import co.edu.udea.SalasInfo.utils.RoleName;
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
