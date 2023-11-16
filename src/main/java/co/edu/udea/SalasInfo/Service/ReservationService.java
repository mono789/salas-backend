package co.edu.udea.SalasInfo.Service;

import co.edu.udea.SalasInfo.DAO.ReservationRepository;
import co.edu.udea.SalasInfo.Model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

@Service
public class ReservationService {
    @Autowired
    ReservationRepository reservationRepository;

    public List<Reservation> findAll(){
        //capturar y enviar los elementos de la bas de datos
        return reservationRepository.findAll();

    }

    //Lista de salones que estan libre en una hora especifica
    public List<Reservation> freeAll(@PathVariable String hora1){
        //formato como se recibe la hora-->  2023-10-25T15:30:00.000+00:00

        //capturo todas las reservas
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

        // Convierte el String a OffsetDateTime
        OffsetDateTime hora = OffsetDateTime.parse(hora1, formato);
        List<Reservation> reservations = reservationRepository.findAll();
        //lista de salones que no estan dentro de la consulta de reservas
        List<Reservation> free=new ArrayList<>();
        // evaluo si la fecha que paso se encuentra entre las fechas de ese dia horas de startsAt o endsAt
        for (Reservation reservation : reservations) {
            if (reservation.getStartsAt() != null) {
                LocalDateTime fechaReserva = reservation.getStartsAt();

                //System.out.println(j[0]);
                if (!fechaReserva.equals(hora.toLocalDateTime())) {
                    //evaluo si el salon esta libre en  esa Hora
                    LocalTime horaInicio = LocalTime.of(reservation.getStartsAt().getHour(), reservation.getStartsAt().getMinute());
                    LocalTime horaFin = LocalTime.of(reservation.getEndsAt().getHour(), reservation.getEndsAt().getMinute());

                    LocalTime horaFecha = hora.toLocalTime();
                    if (!(horaFecha.isAfter(horaInicio) && horaFecha.isBefore(horaFin))) {
                        free.add(reservation);
                    }
                } else {
                    free.add(reservation);
                }
            }}
            return free;
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

    //crear un Nuevo elemento
    public ResponseEntity<Reservation> save(@RequestBody Reservation reservation){
        Optional<Reservation> foundReservation = reservationRepository.findFirstByStartsAt(reservation.getStartsAt());
        if(foundReservation.isPresent()) return ResponseEntity.badRequest().build();
        Reservation result= reservationRepository.save(reservation);
        return  ResponseEntity.ok(result);
    }

    //borrar una reserva de la DB con un id de reserva
    public ResponseEntity<Reservation> delete(@PathVariable Integer reservationId){

        if (!reservationRepository.existsById(reservationId)) { //si el Id NO existe (NUMEO MUY GRANDE)
            return ResponseEntity.notFound().build();
        }
        reservationRepository.deleteById(reservationId);
        return ResponseEntity.noContent().build();

    }

    //actualizar una reserva existente
    public ResponseEntity<Reservation> update(@RequestBody Reservation reservation){

        if(reservation.getReservationId()==null){ //no le mande un id
            return ResponseEntity.badRequest().build();
        }
        if(!reservationRepository.existsById(reservation.getReservationId())){ //si el Id NO existe (NUMERO MUY GRANDE)
            return ResponseEntity.notFound().build();
        }
        //de lo contrario
        Reservation result= reservationRepository.save(reservation);
        return ResponseEntity.ok(result);


    }

    /**
     * Updates start and end dates of the class Reservations every day
     * and
     */
    @Scheduled(fixedRate = 7 * 24 * 60 * 60 * 1000) // Every day
    public void updateClassDates(){
        // Retrieve the list of class reservations
        List<Reservation> classes = reservationRepository.findByReservationType(1);

        for (Reservation classReservation : classes){
            // Get class dates
            assert classReservation != null;
            LocalDateTime startDate = classReservation.getStartsAt();
            LocalDateTime endDate = classReservation.getEndsAt();

            // Analyze these dates, set the date to a new week and then update the object in database
            if (startDate.isBefore(LocalDateTime.now())){
                // Only update if the class reservation was before today.
                classReservation.setStartsAt(
                        startDate.with(TemporalAdjusters.next(startDate.getDayOfWeek()))
                );
                classReservation.setEndsAt(
                        endDate.with(TemporalAdjusters.next(endDate.getDayOfWeek()))
                );
                reservationRepository.save(classReservation);
            }
        }
    }
}



