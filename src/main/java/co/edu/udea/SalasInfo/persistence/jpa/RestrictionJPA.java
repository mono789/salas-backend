package co.edu.udea.SalasInfo.persistence.jpa;

import co.edu.udea.SalasInfo.exceptions.EntityNotFoundException;
import co.edu.udea.SalasInfo.model.Restriction;
import co.edu.udea.SalasInfo.persistence.RestrictionDAO;
import co.edu.udea.SalasInfo.repository.RestrictionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
public class RestrictionJPA implements RestrictionDAO {
    private final RestrictionRepository restrictionRepository;

    @Override
    public Restriction findById(Integer id) {
        return restrictionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Restriction.class.getSimpleName(), id));
    }

    @Override
    public List<Restriction> findAll() {
        return restrictionRepository.findAll();
    }

    @Override
    public Restriction save(Restriction restriction) {
        return restrictionRepository.save(restriction);
    }

    @Override
    public Restriction findByDescription(String description) {
        return restrictionRepository.findByDescription(description)
                .orElseThrow(() ->
                        new EntityNotFoundException(Restriction.class.getSimpleName() + " with description " + description + " not found"));
    }

    @Override
    public void deleteById(Integer id) {
        restrictionRepository.deleteById(id);
    }
}
