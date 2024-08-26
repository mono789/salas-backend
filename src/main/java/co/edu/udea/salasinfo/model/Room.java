package co.edu.udea.salasinfo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name="room")
public class Room implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="roomId")
    private Integer roomId;

    @Column(name="computerAmount", nullable = false, length = 3)
    private Integer computerAmount;

    @Column(name="building", nullable = false, length = 16)
    private String building;

    @Column(name="roomNum", nullable = false, length = 3)
    private String roomNum;

    @Column(name="roomName", length = 64)
    private String roomName;

    @Column(name="subRoom",  length = 2)
    private Integer subRoom;

    @JsonIgnore
    @ManyToMany(targetEntity = Application.class, cascade = CascadeType.ALL)
    @JoinTable(
            name = "roomsoftware",
            joinColumns = @JoinColumn(name = "roomId"),
            inverseJoinColumns = @JoinColumn(name = "applicationId")
    )
    private List<Application> software;

    @JsonIgnore
    @ManyToMany(targetEntity = Restriction.class, cascade = CascadeType.ALL)
    @JoinTable(
            name = "roomrestriction",
            joinColumns = @JoinColumn(name = "roomId"),
            inverseJoinColumns = @JoinColumn(name = "restrictionId")
    )

    private List<Restriction> restrictions;

    @JsonIgnore
    @ManyToMany(targetEntity = Implement.class, cascade = CascadeType.ALL)
    @JoinTable(
            name = "roomimplement",
            joinColumns = @JoinColumn(name = "roomId"),
            inverseJoinColumns = @JoinColumn(name = "implementId")
    )
    @JsonProperty("implements")
    private List<Implement> implementList;

    // Constructor
    public Room(Integer roomId, Integer computerAmount, String building, String roomNum, String roomName, Integer subRoom) {
        this.roomId = roomId;
        this.computerAmount = computerAmount;
        this.building = building;
        this.roomNum = roomNum;
        this.roomName = roomName;
        this.subRoom = subRoom;
    }
}
