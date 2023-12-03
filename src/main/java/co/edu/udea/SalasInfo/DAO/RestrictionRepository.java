package co.edu.udea.SalasInfo.DAO;

import co.edu.udea.SalasInfo.Model.Restriction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestrictionRepository extends JpaRepository<Restriction, Integer> {
    public Optional<Restriction> findByDescription(String description);
}
