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
public class Room_State {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="room_state_id")
    private Integer room_state_id;

    @Column(name="computer_amount", nullable = false, length = 3)
    private Integer computer_amount;

}
