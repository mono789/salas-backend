package co.edu.udea.salasinfo.mapper.response;

import co.edu.udea.salasinfo.dto.response.RestrictionResponse;
import co.edu.udea.salasinfo.model.Restriction;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestrictionResponseMapper {
    RestrictionResponse toResponse(Restriction restriction);
    List<RestrictionResponse> toResponses(List<Restriction> restrictions);
}
