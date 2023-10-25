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
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="room_id")
    private Integer room_id;

    @Column(name="computer_amount", nullable = false, length = 3)
    private Integer computer_amount;

    @Column(name="building", nullable = false, length = 16)
    private String building;

    @Column(name="room_num", nullable = false, length = 3)
    private String room_num;

    @Column(name="name", length = 64)
    private String name;

    @Column(name="room_state")
    private String room_state;

    @Column(name="subroom",  length = 2)
    private String subroom;

}
