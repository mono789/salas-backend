package co.edu.udea.salasinfo.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name="reservationstate")
public class ReservationState implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="reservationStateId")
    private Integer reservationStateId;

    @Column(name="description", nullable = false, length = 20)
    private String description;

    public ReservationState(Integer reservationStateId) {
        this.reservationStateId = reservationStateId;
    }
}
