package co.edu.udea.salasinfo.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ReservationStateDTO {
    private Integer reservationStateId;
    private String description;
}
