package co.edu.udea.salasinfo.mapper.request;

import co.edu.udea.salasinfo.dto.request.ClassReservationRequest;
import co.edu.udea.salasinfo.dto.request.ReservationRequest;
import co.edu.udea.salasinfo.model.Reservation;
import co.edu.udea.salasinfo.model.Room;
import co.edu.udea.salasinfo.model.User;
import co.edu.udea.salasinfo.utils.Generated;
import org.mapstruct.AnnotateWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@AnnotateWith(Generated.class)
@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReservationRequestMapper {
    default Room longToRoom(Long id) {
        return Room.builder().id(id).build();
    }

    default User stringToUser(String id) {
        return User.builder().id(id).build();
    }

    @Mapping(target = "user", source = "userId")
    @Mapping(target = "room", source = "roomId")
    Reservation toEntity(ReservationRequest request);

    List<Reservation> toEntities(List<ReservationRequest> requests);

    ReservationRequest toRequest(ClassReservationRequest classReservationRequest);
}
