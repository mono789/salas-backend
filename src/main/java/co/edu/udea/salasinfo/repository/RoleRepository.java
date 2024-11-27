package co.edu.udea.salasinfo.repository;

import co.edu.udea.salasinfo.model.Role;
import co.edu.udea.salasinfo.utils.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(RoleName roleName);
}