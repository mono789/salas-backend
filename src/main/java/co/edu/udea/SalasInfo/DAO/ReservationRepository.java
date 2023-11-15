package co.edu.udea.SalasInfo.DAO;

import co.edu.udea.SalasInfo.Model.Reservation;
import co.edu.udea.SalasInfo.Model.ReservationState;
import co.edu.udea.SalasInfo.Model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
   // List<Reservation> findAllByRooms(Integer roomId);
    List<Reservation> findByReservationType(Integer reservationType);

}
