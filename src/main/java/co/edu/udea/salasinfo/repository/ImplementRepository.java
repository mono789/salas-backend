package co.edu.udea.salasinfo.repository;

import co.edu.udea.salasinfo.model.Implement;
import co.edu.udea.salasinfo.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImplementRepository extends JpaRepository<Implement, Long> {
}
