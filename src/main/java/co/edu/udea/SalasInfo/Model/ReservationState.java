package co.edu.udea.SalasInfo.Model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name="reservationstate")
public class ReservationState {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="reservationStateId")
    private Integer reservationStateId;

    @Column(name="description", nullable = false, length = 20)
    private String description;

    public ReservationState(Integer reservationStateId) {
        this.reservationStateId = reservationStateId;
    }
}
