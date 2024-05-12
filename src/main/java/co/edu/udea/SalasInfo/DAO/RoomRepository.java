package co.edu.udea.SalasInfo.DAO;

import co.edu.udea.SalasInfo.Model.Application;
import co.edu.udea.SalasInfo.Model.Implement;
import co.edu.udea.SalasInfo.Model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Integer> {
    public List<Room> findRoomsBySoftwareContaining(Application application);
    public List<Room> findRoomsByImplementListContaining(Implement implement);
    }
