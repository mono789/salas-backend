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
@Table(name = "implement")
public class Implement implements Serializable {

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "implementId")
    private Integer implementId;

    @Column(name = "implementName")
    private String implementName;

    @Column(name = "state")
    private String state;

    @JsonIgnore
    @ManyToMany(
            targetEntity = Room.class,
            mappedBy = "implementList",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private transient List <Room> rooms;

    public Implement(Integer implementId, String implementName, String state) {
        this.implementId = implementId;
        this.implementName = implementName;
        this.state = state;
    }
}
