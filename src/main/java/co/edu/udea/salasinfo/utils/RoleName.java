package co.edu.udea.salasinfo.utils;

import org.springframework.security.core.GrantedAuthority;

public enum RoleName implements GrantedAuthority {

    ADMIN,
    PROFESSOR,
    MONITOR,
    USER;

    @Override
    public String getAuthority() {
        return name();
    }
}