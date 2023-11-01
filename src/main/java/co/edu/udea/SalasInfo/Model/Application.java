package co.edu.udea.SalasInfo.Model;

import jakarta.persistence.*;
import lombok.*;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "applicationId")
    private Integer applicationId;
    @Column(name = "applicationName")
    private String applicationName;
    @Column(name = "version")
    private String version;

}
