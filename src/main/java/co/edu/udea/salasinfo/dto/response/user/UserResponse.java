package co.edu.udea.salasinfo.dto.response.user;

import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserResponse {
    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private RoleResponse role;
}