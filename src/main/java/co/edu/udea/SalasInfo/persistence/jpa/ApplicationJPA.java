package co.edu.udea.SalasInfo.persistence.jpa;

import co.edu.udea.SalasInfo.exceptions.EntityNotFoundException;
import co.edu.udea.SalasInfo.model.Application;
import co.edu.udea.SalasInfo.persistence.ApplicationDAO;
import co.edu.udea.SalasInfo.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ApplicationJPA implements ApplicationDAO {
    private final ApplicationRepository applicationRepository;


    @Override
    public Application findById(Integer id) {
        return applicationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Application.class.getSimpleName(), id));
    }

    @Override
    public List<Application> findAll() {
        return applicationRepository.findAll();
    }
}
