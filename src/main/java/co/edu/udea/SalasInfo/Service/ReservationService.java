package co.edu.udea.SalasInfo.Service;

import co.edu.udea.SalasInfo.DAO.ReservationRepository;
import co.edu.udea.SalasInfo.Model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
        List<Reservation> Lista=reservationRepository.findAll();
        //lista de salones que no estan dentro de la consulta de reservas
        List<Reservation> free=new ArrayList<>();
        // evaluo si la fecha que paso se encuentra entre las fechas de ese dia horas de startsAt o endsAt
        for (Reservation i : Lista) {
            //System.out.println(i.getStartsAt());
            //System.out.println(hora);
            if (i.getStartsAt() != null) {
                String[] j = i.getStartsAt().toString().split(" ");
                //
                //System.out.println(j[0]);
                if (j[0].equals(hora.toLocalDate().toString())) {
                    //evaluo si el salon esta libre en  esa Hora
                    LocalTime horaInicio = LocalTime.of(i.getStartsAt().getHours(), i.getStartsAt().getMinutes());
                    LocalTime horaFin = LocalTime.of(i.getEndsAt().getHours(), i.getEndsAt().getMinutes());

                    LocalTime horaFecha = hora.toLocalTime();
                    if (!(horaFecha.isAfter(horaInicio) && horaFecha.isBefore(horaFin))) {
                        free.add(i);
                    }
                } else {
                    free.add(i);
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

        //guardar
        Reservation result= reservationRepository.save(reservation);

        return  ResponseEntity.ok(result);
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
        //System.out.println(reservationRepository.existsById(reservation.getReservationId()));
        if(!reservationRepository.existsById(reservation.getReservationId())){ //si el Id NO existe (NUMEO MUY GRANDE)
            return ResponseEntity.notFound().build();
        }
        //de lo contrario
        Reservation result= reservationRepository.save(reservation);
        return ResponseEntity.ok(result);


    }


}



