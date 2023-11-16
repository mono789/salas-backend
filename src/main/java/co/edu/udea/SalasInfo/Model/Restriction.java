package co.edu.udea.SalasInfo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "restriction")
public class Restriction {
    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "restrictionId")
    private Integer restrictionId;

    @Column(name = "description")
    private String description;

    @JsonIgnore
    @ManyToMany(
            targetEntity = Room.class,
            mappedBy = "restrictions",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    List<Room> rooms;

    // Constructor
    public Restriction(Integer restrictionId, String description) {
        this.restrictionId = restrictionId;
        this.description = description;
    }
}
