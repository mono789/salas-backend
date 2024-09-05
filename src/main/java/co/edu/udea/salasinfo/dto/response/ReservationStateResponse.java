package co.edu.udea.salasinfo.dto.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ReservationStateResponse {
    private Long reservationStateId;
    private String description;
}
