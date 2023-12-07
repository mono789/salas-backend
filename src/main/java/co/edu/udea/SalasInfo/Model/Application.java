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
@Table(name="application")
public class Application {
    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "applicationId")
    private Integer applicationId;

    @Column(name = "applicationName")
    private String applicationName;

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

    //Constructor
    public Application(Integer applicationId, String applicationName, String version) {
        this.applicationId = applicationId;
        this.applicationName = applicationName;
        this.version = version;
    }
}
