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
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="reservation_id")
    private Integer reservation_id;

    @Column(name="activity_name", nullable = false, length = 64)
    private String activity_name;

    @Column(name="description", nullable = false, length = 512)
    private String description;

    @Column(name="starts_at", nullable = false)
    private Date starts_at;

    @Column(name="ends_at", nullable = false)
    private Date ends_at;

}
