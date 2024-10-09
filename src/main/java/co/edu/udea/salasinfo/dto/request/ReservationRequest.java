package co.edu.udea.salasinfo.dto.request;

import co.edu.udea.salasinfo.utils.Constants;
import co.edu.udea.salasinfo.utils.enums.ReservationType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ReservationRequest {
    @NotNull(message = Constants.ACTIVITY_NAME_FIELD_NOT_NULL_MESSAGE)
    private String activityName;

    @NotNull(message = Constants.ACTIVITY_DESCRIPTION_FIELD_NOT_NULL_MESSAGE)
    private String activityDescription;

    @NotNull(message = Constants.STARTS_AT_FIELD_NOT_NULL_MESSAGE)
    @Past(message = Constants.STARTS_AT_FIELD_NOT_PAST_MESSAGE)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startsAt;

    @NotNull(message = Constants.ENDS_AT_FIELD_NOT_NULL_MESSAGE)
    @Past(message = Constants.ENDS_AT_FIELD_NOT_PAST_MESSAGE)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endsAt;

    @NotNull(message = Constants.TYPE_FIELD_NOT_NULL_MESSAGE)
    private ReservationType type;

    @NotNull(message = Constants.USER_ID_FIELD_NOT_NULL_MESSAGE)
    private String userId;

    @NotNull(message = Constants.ROOM_ID_FIELD_NOT_NULL_MESSAGE)
    private Long roomId;
}