package co.edu.udea.salasinfo.persistence;

import co.edu.udea.salasinfo.model.Role;
import co.edu.udea.salasinfo.utils.enums.RoleName;

public interface RoleDAO {
    Role findByRoleName(RoleName roleName);
}
