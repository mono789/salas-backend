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
@Table(name="application")
public class Application implements Serializable {
    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "applicationId")
    private Long id;

    @Column(name = "applicationName")
    private String name;

    @Column(name = "version")
    private String version;

    @JsonIgnore
    @ManyToMany(
            targetEntity = Room.class,
            mappedBy = "software",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Room> rooms;
}
