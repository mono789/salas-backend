package co.edu.udea.SalasInfo.service;

import co.edu.udea.SalasInfo.dto.RestrictionDTO;
import co.edu.udea.SalasInfo.model.Restriction;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RestrictionService {
    List<RestrictionDTO> findAllRestrictions();
    RestrictionDTO createRestriction(RestrictionDTO restriction);
    RestrictionDTO findRestrictionById(Integer id) ;
    RestrictionDTO deleteRestriction(Integer id);
}
