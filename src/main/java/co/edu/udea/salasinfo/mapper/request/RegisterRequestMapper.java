package co.edu.udea.salasinfo.mapper.request;

import co.edu.udea.salasinfo.dto.request.auth.RegisterRequest;
import co.edu.udea.salasinfo.model.User;
import co.edu.udea.salasinfo.utils.Generated;
import org.mapstruct.AnnotateWith;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@AnnotateWith(Generated.class)
@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RegisterRequestMapper {
    User toEntity(RegisterRequest registerRequest);
    List<User> toEntities(List<RegisterRequest> registerRequests);
}
