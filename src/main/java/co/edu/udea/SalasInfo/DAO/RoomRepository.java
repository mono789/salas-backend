package co.edu.udea.SalasInfo.DAO;

import co.edu.udea.SalasInfo.Model.Application;
import co.edu.udea.SalasInfo.Model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Integer> {
}
