package co.edu.udea.salasinfo.repository;

import co.edu.udea.salasinfo.model.Application;
import co.edu.udea.salasinfo.model.Implement;
import co.edu.udea.salasinfo.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Integer> {
    public List<Room> findRoomsBySoftwareContaining(Application application);
    public List<Room> findRoomsByImplementListContaining(Implement implement);
    }
