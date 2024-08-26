package co.edu.udea.salasinfo.service;

import co.edu.udea.salasinfo.dto.RestrictionDTO;

import java.util.List;

public interface RestrictionService {
    List<RestrictionDTO> findAllRestrictions();
    RestrictionDTO createRestriction(RestrictionDTO restriction);
    RestrictionDTO findRestrictionById(Integer id) ;
    RestrictionDTO deleteRestriction(Integer id);
}
