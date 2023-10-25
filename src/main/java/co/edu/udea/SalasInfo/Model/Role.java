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
@Table(name="role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="roleId")
    private Integer roleId;

    @Column(name="roleName", nullable = false, length = 64)
    private String roleName;
}
