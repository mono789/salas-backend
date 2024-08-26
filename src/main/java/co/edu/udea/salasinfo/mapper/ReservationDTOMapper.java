package co.edu.udea.salasinfo.mapper;

import co.edu.udea.salasinfo.dto.ReservationDTO;
import co.edu.udea.salasinfo.model.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReservationDTOMapper {
    ReservationDTO toDTO(Reservation reservation);
    Reservation toEntity(ReservationDTO reservationDTO);
    List<ReservationDTO> toDTOs(List<Reservation> reservations);
    List<Reservation> toEntities(List<ReservationDTO> reservationDTOs);
}
