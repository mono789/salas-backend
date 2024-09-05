package co.edu.udea.salasinfo.repository;

import co.edu.udea.salasinfo.model.Application;
import co.edu.udea.salasinfo.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
}
