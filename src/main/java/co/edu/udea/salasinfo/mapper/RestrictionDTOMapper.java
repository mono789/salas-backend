package co.edu.udea.salasinfo.mapper;

import co.edu.udea.salasinfo.dto.RestrictionDTO;
import co.edu.udea.salasinfo.model.Restriction;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestrictionDTOMapper {
    RestrictionDTO toDTO(Restriction restriction);
    Restriction toEntity(RestrictionDTO restrictionDTO);
    List<RestrictionDTO> toDTOs(List<Restriction> restrictions);
    List<Restriction> toEntities(List<RestrictionDTO> restrictionDTOs);
}
