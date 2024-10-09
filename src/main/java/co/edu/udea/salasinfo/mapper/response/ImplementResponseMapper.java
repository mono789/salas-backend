package co.edu.udea.salasinfo.mapper.response;

import co.edu.udea.salasinfo.dto.response.ImplementResponse;
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
public interface ImplementResponseMapper {
    ImplementResponse toResponse(Implement implement);

    List<ImplementResponse> toResponses(List<Implement> implement);
}
