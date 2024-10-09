package co.edu.udea.salasinfo.dto.request.auth;

import co.edu.udea.salasinfo.utils.Constants;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthenticationRequest {
    @NotNull(message = Constants.EMAIL_FIELD_NOT_NULL_MESSAGE)
    @Pattern(regexp = Constants.EMAIL_REGEX_RFC5322)
    private String email;

    @NotNull(message = Constants.PASSWORD_FIELD_NOT_NULL_MESSAGE)
    private String password;
}
