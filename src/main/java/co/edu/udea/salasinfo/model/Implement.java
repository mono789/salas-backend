package co.edu.udea.salasinfo.model;

import co.edu.udea.salasinfo.utils.enums.IState;
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
@Table(name = "implement")
public class Implement implements Serializable {

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "implementId")
    private Long implementId;

    @Column(name = "implementName")
    private String implementName;

    @Column(name = "state")
    private IState state;

    @JsonIgnore
    @ManyToMany(
            targetEntity = Room.class,
            mappedBy = "implementList",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List <Room> rooms;
}
