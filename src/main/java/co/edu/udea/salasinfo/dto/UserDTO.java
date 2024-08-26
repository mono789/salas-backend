package co.edu.udea.salasinfo.dto;

import co.edu.udea.salasinfo.utils.RoleName;
import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserDTO {
    private Long userId;
    private String firstname;
    private String lastname;
    private String email;    //email
    private String password;
    private RoleName roleName;
}