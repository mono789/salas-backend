package co.edu.udea.SalasInfo.persistence;

import co.edu.udea.SalasInfo.model.Application;
import co.edu.udea.SalasInfo.model.Implement;
import co.edu.udea.SalasInfo.model.Room;

import java.util.List;

public interface RoomDAO{
    List<Room> findRoomsBySoftwareContaining(Application application);
    List<Room> findRoomsByImplementListContaining(Implement implement);
    Room findById(Integer id);
    List<Room> findAll();
    Room save(Room room);
    void deleteById(int id);
}
