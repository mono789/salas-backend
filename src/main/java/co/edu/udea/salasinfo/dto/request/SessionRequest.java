package co.edu.udea.salasinfo.dto.request;

import co.edu.udea.salasinfo.utils.Constants;
import co.edu.udea.salasinfo.utils.enums.WeekDay;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(type = "String", pattern = Constants.HOUR_FORMAT)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    private LocalTime startsAt;

    @NotNull(message = Constants.ENDS_AT_FIELD_NOT_NULL_MESSAGE)
    @Schema(type = "String", pattern = Constants.HOUR_FORMAT)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    private LocalTime endsAt;
}
