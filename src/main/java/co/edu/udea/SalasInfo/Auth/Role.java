package co.edu.udea.SalasInfo.Auth;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    ADMIN,
    PROFESSOR,
    MONITOR,
    USER;

    @Override
    public String getAuthority() {
        return name();
    }
}