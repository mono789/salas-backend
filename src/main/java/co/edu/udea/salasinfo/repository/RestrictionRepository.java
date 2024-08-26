package co.edu.udea.salasinfo.repository;

import co.edu.udea.salasinfo.model.Restriction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestrictionRepository extends JpaRepository<Restriction, Integer> {
    public Optional<Restriction> findByDescription(String description);
}
