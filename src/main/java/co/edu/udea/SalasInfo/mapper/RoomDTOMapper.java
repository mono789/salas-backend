package co.edu.udea.SalasInfo.mapper;

import co.edu.udea.SalasInfo.dto.RoomDTO;
import co.edu.udea.SalasInfo.model.Room;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoomDTOMapper {
    RoomDTO toDTO(Room room);
    Room toEntity(RoomDTO roomDTO);
    List<RoomDTO> toDTOs(List<Room> rooms);
    List<Room> toEntities(List<RoomDTO> roomDTOs);
}
