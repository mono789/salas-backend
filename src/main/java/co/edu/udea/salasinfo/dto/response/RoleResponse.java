package co.edu.udea.salasinfo.dto.response;

import co.edu.udea.salasinfo.utils.enums.RoleName;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RoleResponse {
    private Long id;
    private RoleName roleName;
}
