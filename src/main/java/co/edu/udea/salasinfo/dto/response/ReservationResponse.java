package co.edu.udea.salasinfo.dto.response;

import co.edu.udea.salasinfo.dto.response.room.RoomResponse;
import co.edu.udea.salasinfo.utils.enums.ReservationType;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ReservationResponse {
    private Long id;
    private String activityName;
    private String activityDescription;
    private LocalDateTime startsAt;
    private LocalDateTime endsAt;
    private ReservationType type;
    private UserResponse user;
    private RoomResponse room;
    private ReservationStateResponse reservationState;
}
