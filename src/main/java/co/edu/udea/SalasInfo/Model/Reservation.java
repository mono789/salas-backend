package co.edu.udea.SalasInfo.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//@ToString
@Builder
@Table(name="reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="reservationId")
    private Integer reservationId;

    @Column(name="activityName", nullable = false, length = 64)
    private String activityName;

    @Column(name="activityDescription", nullable = false, length = 512)
    private String activityDescription;

    @Column(name="startsAt", nullable = false)
    private LocalDateTime startsAt;

    @Column(name="endsAt", nullable = false)
    private LocalDateTime endsAt;

    @Column(name="reservationType", nullable = false)
    private Integer reservationType;

    /*
    @Column(name="userId")
    private String userId;

    @Column(name = "roomId")
    private Integer roomId;

    @Column(name = "reservationStateId")
    private Integer reservationStateId;

     */
    @ManyToOne
    @JoinColumn(name="userId", referencedColumnName = "userId")
    private User userId;

    @ManyToOne
    @JoinColumn(name="roomId", referencedColumnName = "roomId")
    private Room roomId;

    @ManyToOne
    @JoinColumn(name="reservationStateId", referencedColumnName = "reservationStateId")
    private ReservationState reservationStateId;

    @Override
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
