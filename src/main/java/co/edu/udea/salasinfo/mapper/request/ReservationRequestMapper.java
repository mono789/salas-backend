package co.edu.udea.salasinfo.mapper.request;

import co.edu.udea.salasinfo.dto.request.ReservationRequest;
import co.edu.udea.salasinfo.model.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReservationRequestMapper {
    Reservation toEntity(ReservationRequest request);
    List<Reservation> toEntity(List<ReservationRequest> requests);
}
