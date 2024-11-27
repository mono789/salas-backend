package co.edu.udea.salasinfo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Builder
@Table(name = "restriction")
public class Restriction implements Serializable {
    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restrictionId")
    private Long id;

    @Column(name = "description")
    private String description;

    @JsonIgnore
    @ManyToMany(
            targetEntity = Room.class,
            mappedBy = "restrictions",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Room> rooms;

    // Falta ponr las otras relaciones

}
