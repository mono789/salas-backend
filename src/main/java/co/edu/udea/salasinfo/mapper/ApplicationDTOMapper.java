package co.edu.udea.salasinfo.mapper;

import co.edu.udea.salasinfo.dto.ApplicationDTO;
import co.edu.udea.salasinfo.model.Application;
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
