package co.edu.udea.salasinfo.mapper.request;

import co.edu.udea.salasinfo.dto.request.ImplementRequest;
import co.edu.udea.salasinfo.model.Implement;
import co.edu.udea.salasinfo.utils.Generated;
import org.mapstruct.AnnotateWith;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@AnnotateWith(Generated.class)
@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ImplementRequestMapper {
    Implement toEntity(ImplementRequest request);

    List<Implement> toEntity(List<ImplementRequest> requests);
}
