package co.edu.udea.salasinfo.mapper.response;

import co.edu.udea.salasinfo.dto.response.ReservationStateResponse;
import co.edu.udea.salasinfo.model.ReservationState;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReservationStateResponseMapper {
    ReservationStateResponse toResponse(ReservationState reservationState);
    List<ReservationStateResponse> toResponses(List<ReservationState> reservationStates);
}
