package co.edu.udea.salasinfo.dto.request.user;

import co.edu.udea.salasinfo.utils.enums.RoleName;
import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserRoleRequest {
    private RoleName roleName;
}