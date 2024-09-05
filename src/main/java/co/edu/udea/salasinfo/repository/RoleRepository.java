package co.edu.udea.salasinfo.repository;

import co.edu.udea.salasinfo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}