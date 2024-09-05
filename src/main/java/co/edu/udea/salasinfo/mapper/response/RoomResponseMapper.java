package co.edu.udea.salasinfo.mapper.response;

import co.edu.udea.salasinfo.dto.response.room.RoomResponse;
import co.edu.udea.salasinfo.model.Room;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoomResponseMapper {
    RoomResponse toResponse(Room room);
    List<RoomResponse> toResponses(List<Room> rooms);
}
