package co.edu.udea.salasinfo.mapper.response;

import co.edu.udea.salasinfo.dto.response.room.SpecificRoomResponse;
import co.edu.udea.salasinfo.model.Room;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SpecificRoomResponseMapper {
    SpecificRoomResponse toResponse(Room room);
    List<SpecificRoomResponse> toResponses(List<Room> rooms);
}
