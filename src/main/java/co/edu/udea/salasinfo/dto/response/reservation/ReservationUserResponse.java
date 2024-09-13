package co.edu.udea.salasinfo.dto.response.reservation;

import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ReservationUserResponse {
    private String id;
    private String firstname;
    private String lastname;
    private String email;    //email
}