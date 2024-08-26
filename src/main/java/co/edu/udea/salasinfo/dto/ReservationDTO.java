package co.edu.udea.salasinfo.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ReservationDTO {
    private Integer reservationId;
    private String activityName;
    private String activityDescription;
    private LocalDateTime startsAt;
    private LocalDateTime endsAt;
    private Integer reservationType;
    private UserDTO userId;
    private RoomDTO roomId;
    private ReservationStateDTO reservationStateId;

    public String toString() {
        return "Reservation{" +
                "reservationId=" + reservationId +
                ", activityName='" + activityName + '\'' +
                ", activityDescription='" + activityDescription + '\'' +
                ", startsAt=" + startsAt +
                ", endsAt=" + endsAt +
                ", reservationType=" + reservationType +
                '}';
    }
}
