package co.edu.udea.salasinfo.mapper.response;

import co.edu.udea.salasinfo.dto.response.ApplicationResponse;
import co.edu.udea.salasinfo.model.Application;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ApplicationResponseMapper {
    ApplicationResponse toResponse(Application application);
    List<ApplicationResponse> toResponses(List<Application> applications);
}
