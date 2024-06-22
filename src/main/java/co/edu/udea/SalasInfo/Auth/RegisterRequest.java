package co.edu.udea.SalasInfo.Auth;

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
