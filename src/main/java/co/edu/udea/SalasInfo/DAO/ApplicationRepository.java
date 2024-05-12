package co.edu.udea.SalasInfo.DAO;

import co.edu.udea.SalasInfo.Model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {
}
