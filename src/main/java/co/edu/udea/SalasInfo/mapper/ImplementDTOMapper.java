package co.edu.udea.SalasInfo.mapper;

import co.edu.udea.SalasInfo.dto.ImplementDTO;
import co.edu.udea.SalasInfo.model.Implement;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ImplementDTOMapper {
    ImplementDTO toDTO(Implement implement);
    Implement toEntity(ImplementDTO implementDTO);
    List<ImplementDTO> toDTOs(List<Implement> implementList);
    List<Implement> toEntities(List<ImplementDTO> implementDTOs);
}
