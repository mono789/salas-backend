package co.edu.udea.SalasInfo.dto;

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
