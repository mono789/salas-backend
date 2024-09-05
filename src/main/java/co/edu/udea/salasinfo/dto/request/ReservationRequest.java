package co.edu.udea.salasinfo.dto.request;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ReservationRequest {
    private String activityName;
    private String activityDescription;
    private LocalDateTime startsAt;
    private LocalDateTime endsAt;
    private Integer reservationType;
    private UserRequest user;
    private RoomRequest room;
    private ReservationStateRequest reservationState;
}
