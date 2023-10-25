package co.edu.udea.SalasInfo.DAO;

import co.edu.udea.SalasInfo.Model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Integer> {
}
