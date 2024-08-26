package co.edu.udea.salasinfo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//@ToString
@Builder
@Table(name="reservation")
public class Reservation implements Serializable {
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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="customerId", referencedColumnName = "customerId")
    private User userId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="roomId", referencedColumnName = "roomId")
    private Room room;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="reservationStateId", referencedColumnName = "reservationStateId")
    private ReservationState reservationState;

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
