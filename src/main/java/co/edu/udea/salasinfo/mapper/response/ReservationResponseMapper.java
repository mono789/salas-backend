package co.edu.udea.salasinfo.mapper.response;

import co.edu.udea.salasinfo.dto.response.ReservationResponse;
import co.edu.udea.salasinfo.model.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReservationResponseMapper {
    ReservationResponse toResponse(Reservation reservation);
    List<ReservationResponse> toResponses(List<Reservation> reservations);
}
