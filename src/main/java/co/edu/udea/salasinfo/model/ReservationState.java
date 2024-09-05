package co.edu.udea.salasinfo.model;

import co.edu.udea.salasinfo.utils.enums.RStatus;
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
@Builder
@Table(name="reservationstate")
public class ReservationState implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="reservationStateId")
    private Long id;

    @Column(name="description", nullable = false, length = 20)
    private RStatus state;

}
