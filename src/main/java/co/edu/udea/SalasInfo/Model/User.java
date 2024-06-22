package co.edu.udea.SalasInfo.Model;

import co.edu.udea.SalasInfo.Auth.Role;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="customer",uniqueConstraints = {@UniqueConstraint(columnNames = {"username","customerId"})} )
public class User implements UserDetails {
    @Id
    @Column(name = "customerid", length = 20)
    Long customerId;
    String firstname;
    String lastname;
    String username;    //email
    String password;
    Role role;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.toString()));
        return authorities;
    }

    @Override
    public String getUsername() {
        return username;
    }

    //todos los booleans retornan true porque es el token el que va a decir que pasa, por lo tanto lo que retornen no afecta
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}