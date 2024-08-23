package co.edu.udea.SalasInfo.repository;

import co.edu.udea.SalasInfo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}