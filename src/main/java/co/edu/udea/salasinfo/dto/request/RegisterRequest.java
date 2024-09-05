package co.edu.udea.salasinfo.dto.request;

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
    String email;
    String document;
    String password;
    String firstname;
    String lastname;

}
