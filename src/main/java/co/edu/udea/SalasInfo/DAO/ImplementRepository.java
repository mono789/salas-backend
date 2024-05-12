package co.edu.udea.SalasInfo.DAO;

import co.edu.udea.SalasInfo.Model.Implement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImplementRepository extends JpaRepository<Implement, Integer> {
}
