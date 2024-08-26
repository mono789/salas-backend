package co.edu.udea.salasinfo.mapper;

import co.edu.udea.salasinfo.dto.ApplicationDTO;
import co.edu.udea.salasinfo.dto.ImplementDTO;
import co.edu.udea.salasinfo.dto.RestrictionDTO;
import co.edu.udea.salasinfo.dto.RoomDTO;
import co.edu.udea.salasinfo.model.Application;
import co.edu.udea.salasinfo.model.Implement;
import co.edu.udea.salasinfo.model.Restriction;
import co.edu.udea.salasinfo.model.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoomDTOMapper {

    @Mapping(target = "rooms", ignore = true)
    Application toApplication(ApplicationDTO applicationDTO);

    @Mapping(target = "rooms", ignore = true)
    ApplicationDTO toApplicationDTO(Application application);

    @Mapping(target = "rooms", ignore = true)
    Implement toImplement(ImplementDTO implementDTO);

    @Mapping(target = "rooms", ignore = true)
    ImplementDTO toImplementDTO(Implement implement);

    @Mapping(target = "rooms", ignore = true)
    Restriction toRestriction(RestrictionDTO restrictionDTO);

    @Mapping(target = "rooms", ignore = true)
    RestrictionDTO toRestrictionDTO(Restriction restriction);

    RoomDTO toDTO(Room room);
    Room toEntity(RoomDTO roomDTO);
    List<RoomDTO> toDTOs(List<Room> rooms);
    List<Room> toEntities(List<RoomDTO> roomDTOs);
}
