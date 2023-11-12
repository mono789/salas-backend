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
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="userId")
    private Integer userId;

    @Column(name="userName", nullable = false, length = 64)
    private String userName;

    @Column(name="lastName", nullable = false, length = 64)
    private String lastName;

    @Column(name="email", nullable = false, length = 64, unique=true)
    private String email;

    @ManyToOne
    @JoinColumn(name="roleId", referencedColumnName = "roleId")
    private Role roleId;
}
