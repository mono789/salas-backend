package co.edu.udea.salasinfo.persistence;

import co.edu.udea.salasinfo.model.Application;
import co.edu.udea.salasinfo.model.Implement;
import co.edu.udea.salasinfo.model.Room;

import java.util.List;

public interface RoomDAO{
    Room findById(Long id);
    List<Room> findAll();
    Room save(Room room);
    void deleteById(Long id);
}
