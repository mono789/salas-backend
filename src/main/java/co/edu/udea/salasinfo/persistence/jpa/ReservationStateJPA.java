package co.edu.udea.salasinfo.persistence.jpa;

import co.edu.udea.salasinfo.exceptions.EntityNotFoundException;
import co.edu.udea.salasinfo.model.ReservationState;
import co.edu.udea.salasinfo.persistence.ReservationStateDAO;
import co.edu.udea.salasinfo.repository.ReservationStateRepository;
import co.edu.udea.salasinfo.utils.enums.RStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservationStateJPA implements ReservationStateDAO {
    private final ReservationStateRepository reservationStateRepository;

    @Override
    public ReservationState findByState(RStatus status) {
        return reservationStateRepository.findByState(status).orElseThrow(() -> new EntityNotFoundException(ReservationState.class.getName(), status.name()));
    }
}
