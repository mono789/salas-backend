package co.edu.udea.SalasInfo.dto;

import co.edu.udea.SalasInfo.utils.RoleName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long customerId;
    private String firstname;
    private String lastname;
    private String email;    //email
    private String password;
    private RoleName roleName;
}