package co.edu.udea.salasinfo.persistence.jpa;

import co.edu.udea.salasinfo.exceptions.EntityNotFoundException;
import co.edu.udea.salasinfo.model.Room;
import co.edu.udea.salasinfo.persistence.RoomDAO;
import co.edu.udea.salasinfo.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RoomJPA implements RoomDAO {
    private final RoomRepository roomRepository;

    @Override
    public Room findById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Room.class.getSimpleName(), id));
    }

    @Override
    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    @Override
    public Room save(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public void deleteById(Long id) {
        roomRepository.deleteById(id);
    }
}
