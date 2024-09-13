package co.edu.udea.salasinfo.mapper.request;

import co.edu.udea.salasinfo.dto.request.ReservationStateRequest;
import co.edu.udea.salasinfo.model.ReservationState;
import co.edu.udea.salasinfo.utils.Generated;
import org.mapstruct.AnnotateWith;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@AnnotateWith(Generated.class)
@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReservationStateRequestMapper {
    ReservationState toEntity(ReservationStateRequest request);

    List<ReservationState> toEntities(List<ReservationStateRequest> requests);
}
