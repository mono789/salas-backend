package co.edu.udea.SalasInfo.persistence.jpa;

import co.edu.udea.SalasInfo.exceptions.EntityNotFoundException;
import co.edu.udea.SalasInfo.model.Implement;
import co.edu.udea.SalasInfo.persistence.ImplementDAO;
import co.edu.udea.SalasInfo.repository.ImplementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ImplementJPA implements ImplementDAO {
    private final ImplementRepository implementRepository;

    @Override
    public Implement findById(Integer id) {
        return implementRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Implement.class.getSimpleName(), id));

    }

    @Override
    public List<Implement> findAll() {
        return implementRepository.findAll();
    }
}
