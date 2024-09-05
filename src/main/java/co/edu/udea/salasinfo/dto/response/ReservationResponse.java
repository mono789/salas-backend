package co.edu.udea.salasinfo.dto.response;

import co.edu.udea.salasinfo.dto.response.room.RoomResponse;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ReservationResponse {
    private Long reservationId;
    private String activityName;
    private String activityDescription;
    private LocalDateTime startsAt;
    private LocalDateTime endsAt;
    private Integer reservationType;
    private UserResponse user;
    private RoomResponse room;
    private ReservationStateResponse reservationState;
}
