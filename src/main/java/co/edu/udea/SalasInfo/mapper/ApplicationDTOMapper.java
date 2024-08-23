package co.edu.udea.SalasInfo.mapper;

import co.edu.udea.SalasInfo.dto.ApplicationDTO;
import co.edu.udea.SalasInfo.model.Application;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ApplicationDTOMapper {
    ApplicationDTO toDTO(Application application);
    Application toEntity(ApplicationDTO applicationDTO);
    List<ApplicationDTO> toDTOs(List<Application> applications);
    List<Application> toEntities(List<ApplicationDTO> applicationDTOs);
}
