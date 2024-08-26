package co.edu.udea.salasinfo.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    Integer customerId;
    String username;
    String password;
    String firstname;
    String lastname;

}
