package co.edu.udea.salasinfo.dto.request.user;

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
}