package co.edu.udea.SalasInfo.repository;

import co.edu.udea.SalasInfo.model.Implement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImplementRepository extends JpaRepository<Implement, Integer> {
}
