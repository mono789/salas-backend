package co.edu.udea.SalasInfo.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name="reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="reservationId")
    private Integer reservationId;

    @Column(name="activityName", nullable = false, length = 64)
    private String activityName;

    @Column(name="activityDescription", nullable = false, length = 512)
    private String activityDescription;

    @Column(name="startsAt", nullable = false)
    private Date startsAt;

    @Column(name="endsAt", nullable = false)
    private Date endsAt;

    @ManyToOne
    @JoinColumn(name="userId", referencedColumnName = "userId")
    private User userId;

    @ManyToOne
    @JoinColumn(name="roomId", referencedColumnName = "roomId")
    private Room roomId;

    @ManyToOne
    @JoinColumn(name="reservationStateId", referencedColumnName = "reservationStateId")
    private ReservationState reservationStateId;

}
