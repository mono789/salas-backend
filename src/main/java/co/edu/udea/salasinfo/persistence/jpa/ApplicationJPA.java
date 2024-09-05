package co.edu.udea.salasinfo.persistence.jpa;

import co.edu.udea.salasinfo.exceptions.EntityNotFoundException;
import co.edu.udea.salasinfo.model.Application;
import co.edu.udea.salasinfo.model.Room;
import co.edu.udea.salasinfo.persistence.ApplicationDAO;
import co.edu.udea.salasinfo.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ApplicationJPA implements ApplicationDAO {
    private final ApplicationRepository applicationRepository;


    @Override
    public Application findById(Long id) {
        return applicationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Application.class.getSimpleName(), id));
    }

    @Override
    public List<Application> findAll() {
        return applicationRepository.findAll();
    }

    @Override
    public List<Room> findRoomsByApplicationId(Long id) {
        Application application = findById(id);
        return application.getRooms();
    }
}
