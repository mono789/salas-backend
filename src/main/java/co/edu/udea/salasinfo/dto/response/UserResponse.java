package co.edu.udea.salasinfo.dto.response;

import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserResponse {
    private String userId;
    private String firstname;
    private String lastname;
    private String email;    //email
    private RoleResponse role;
}