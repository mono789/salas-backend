package co.edu.udea.salasinfo.dto.request.auth;

import co.edu.udea.salasinfo.utils.Constants;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotNull(message = Constants.FIRST_NAME_FIELD_NOT_NULL_MESSAGE)
    private String firstname;

    @NotNull(message = Constants.LAST_NAME_FIELD_NOT_NULL_MESSAGE)
    private String lastname;

    @NotNull(message = Constants.EMAIL_FIELD_NOT_NULL_MESSAGE)
    @Pattern(regexp = Constants.EMAIL_REGEX_RFC5322)
    private String email;

    @NotNull(message = Constants.PASSWORD_FIELD_NOT_NULL_MESSAGE)
    private String password;

}
