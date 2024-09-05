package co.edu.udea.salasinfo.dto.response.room;

import co.edu.udea.salasinfo.utils.enums.ReservationType;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RoomScheduleResponse {
    private Long id;
    private String activityName;
    private String activityDescription;
    private LocalDateTime startsAt;
    private LocalDateTime endsAt;
    private ReservationType type;
}
