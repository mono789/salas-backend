package co.edu.udea.salasinfo.persistence.jpa;

import co.edu.udea.salasinfo.exceptions.EntityNotFoundException;
import co.edu.udea.salasinfo.model.Role;
import co.edu.udea.salasinfo.persistence.RoleDAO;
import co.edu.udea.salasinfo.repository.RoleRepository;
import co.edu.udea.salasinfo.utils.enums.RoleName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleJPA implements RoleDAO {
    private final RoleRepository roleRepository;

    @Override
    public Role findByRoleName(RoleName roleName) {
        return roleRepository.findByRoleName(roleName).orElseThrow(() -> new EntityNotFoundException(Role.class.getSimpleName(), roleName.toString()));
    }
}
