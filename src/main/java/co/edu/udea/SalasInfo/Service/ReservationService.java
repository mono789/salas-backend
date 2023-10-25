package co.edu.udea.SalasInfo.Service;

import co.edu.udea.SalasInfo.DAO.ReservationRepository;
import co.edu.udea.SalasInfo.Model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    @Autowired
    ReservationRepository reservationRepository; //lista de aulas

    public List<Reservation> findAll(){
        //capturar y enviar los elementos de la bas de datos
        return reservationRepository.findAll();

    }



    //crear un Nuevo elemento

    public ResponseEntity<Reservation> save(@RequestBody Reservation reservation){

        //guardar
        Reservation result= reservationRepository.save(reservation);

        return  ResponseEntity.ok(result);
    }




    //buscar segun su room

    public ResponseEntity<Reservation> findById(@PathVariable Integer room_id){
        //usamos siempre un optional para no tratar con excepciones de alcance por id, debido a un salon incorrecto
        Optional<Reservation> optionalReservation= reservationRepository.findById(room_id);
        if(optionalReservation.isPresent())
            //si existe lo devuelvo
            return  ResponseEntity.ok(optionalReservation.get());
        else
            //si no existe debolvemos un 404 con un response entity
            return ResponseEntity.notFound().build();
    }




    //borrar una reserva de la DB con un id de reserva

    public ResponseEntity<Reservation> delete(@PathVariable Integer reservation_id){

        if (!reservationRepository.existsById(reservation_id)) { //si el Id NO existe (NUMEO MUY GRANDE)
            return ResponseEntity.notFound().build();
        }
        reservationRepository.deleteById(reservation_id);
        return ResponseEntity.noContent().build();

    }




    //actualizar una reserva existente

    public ResponseEntity<Reservation> update(@RequestBody Reservation reservation){
        if(reservation.getReservationId()==null){ //no le mande un id
            return ResponseEntity.badRequest().build();
        }
        if(!reservationRepository.existsById(reservation.getReservationId())){ //si el Id NO existe (NUMEO MUY GRANDE)
            return ResponseEntity.notFound().build();
        }
        //de lo contrario
        Reservation result= reservationRepository.save(reservation);
        return ResponseEntity.ok(result);


    }


}



