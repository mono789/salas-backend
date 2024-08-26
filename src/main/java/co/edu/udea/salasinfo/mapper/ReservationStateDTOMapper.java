package co.edu.udea.salasinfo.mapper;

import co.edu.udea.salasinfo.dto.ReservationStateDTO;
import co.edu.udea.salasinfo.model.ReservationState;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReservationStateDTOMapper {
    ReservationStateDTO toDTO(ReservationState reservationState);
    ReservationState toEntity(ReservationStateDTO reservationStateDTO);
    List<ReservationStateDTO> toDTOs(List<ReservationState> reservationStates);
    List<ReservationState> toEntities(List<ReservationStateDTO> reservationStateDTOs);
}
