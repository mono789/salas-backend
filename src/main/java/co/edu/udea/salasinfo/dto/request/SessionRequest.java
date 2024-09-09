package co.edu.udea.salasinfo.dto.request;

import co.edu.udea.salasinfo.utils.Constants;
import co.edu.udea.salasinfo.utils.enums.WeekDay;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SessionRequest {
    @NotNull(message = Constants.DAY_FIELD_NOT_NULL_MESSAGE)
    private WeekDay day;

    @NotNull(message = Constants.STARTS_AT_FIELD_NOT_NULL_MESSAGE)
    private LocalTime startsAt;

    @NotNull(message = Constants.ENDS_AT_FIELD_NOT_NULL_MESSAGE)
    private LocalTime endsAt;
}
