package co.edu.udea.salasinfo.mapper.request;

import co.edu.udea.salasinfo.dto.request.RestrictionRequest;
import co.edu.udea.salasinfo.model.Restriction;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestrictionRequestMapper {
    Restriction toEntity(RestrictionRequest request);
    List<Restriction> toEntities(List<RestrictionRequest> requests);
}
