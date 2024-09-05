package co.edu.udea.salasinfo.dto.request;

import co.edu.udea.salasinfo.utils.enums.RoleName;
import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserRequest {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private RoleName roleName;
}