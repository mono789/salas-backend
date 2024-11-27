package co.edu.udea.salasinfo.persistence;

import co.edu.udea.salasinfo.model.ReservationState;
import co.edu.udea.salasinfo.utils.enums.RStatus;

public interface ReservationStateDAO {
    ReservationState findByState(RStatus status);
}
