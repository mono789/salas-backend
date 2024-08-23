package co.edu.udea.SalasInfo.repository;

import co.edu.udea.SalasInfo.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {
}
