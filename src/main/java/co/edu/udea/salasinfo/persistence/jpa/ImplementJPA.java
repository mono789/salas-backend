package co.edu.udea.salasinfo.persistence.jpa;

import co.edu.udea.salasinfo.exceptions.EntityNotFoundException;
import co.edu.udea.salasinfo.model.Implement;
import co.edu.udea.salasinfo.model.Room;
import co.edu.udea.salasinfo.persistence.ImplementDAO;
import co.edu.udea.salasinfo.repository.ImplementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ImplementJPA implements ImplementDAO {
    private final ImplementRepository implementRepository;

    @Override
    public Implement findById(Long id) {
        return implementRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Implement.class.getSimpleName(), id));

    }

    @Override
    public List<Implement> findAll() {
        return implementRepository.findAll();
    }

    @Override
    public List<Room> findRoomsByImplementId(Long id) {
        Implement implement = findById(id);
        return implement.getRooms();
    }
}
