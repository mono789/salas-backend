package co.edu.udea.SalasInfo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name="customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="customerId")
    private Integer customerId;

    @Column(name="customerName", nullable = false, length = 64)
    private String customerName;

    @Column(name="lastName", nullable = false, length = 64)
    private String lastName;

    @Column(name="email", nullable = false, length = 64, unique=true)
    private String email;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="roleId", referencedColumnName = "roleId")
    private Role roleId;
}
