package co.edu.udea.salasinfo.service.impl;

import co.edu.udea.salasinfo.dto.request.RestrictionRequest;
import co.edu.udea.salasinfo.dto.response.RestrictionResponse;
import co.edu.udea.salasinfo.exceptions.EntityAlreadyExistsException;
import co.edu.udea.salasinfo.exceptions.EntityNotFoundException;
import co.edu.udea.salasinfo.mapper.request.RestrictionRequestMapper;
import co.edu.udea.salasinfo.mapper.response.RestrictionResponseMapper;
import co.edu.udea.salasinfo.persistence.RestrictionDAO;
import co.edu.udea.salasinfo.model.Restriction;
import co.edu.udea.salasinfo.service.RestrictionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestrictionServiceImpl implements RestrictionService {

    private final RestrictionDAO restrictionDAO;
    private final RestrictionResponseMapper restrictionResponseMapper;
    private final RestrictionRequestMapper restrictionRequestMapper;

    /**
     * Retrieves all existent room restrictions.
     * @return A response Entity with the Found Restrictions as body.
     */
    public List<RestrictionResponse> findAllRestrictions(){
        return restrictionResponseMapper.toResponses(restrictionDAO.findAll());
    }

    /**
     * Saves in database the give Restriction if there is not another with the same description.
     * @param restriction The restriction to save.
     * @return A response entity with the saved restriction or a bad request.
     */
    public RestrictionResponse createRestriction(RestrictionRequest restriction){
        try{
            restrictionDAO .findByDescription(restriction.getDescription());
            // Return a bad request if exist a restriction with that description or the created description
            throw new EntityAlreadyExistsException(Restriction.class.getSimpleName());
        } catch (EntityNotFoundException e){
            return restrictionResponseMapper.toResponse(restrictionDAO.save(restrictionRequestMapper.toEntity(restriction)));
        }
    }

    /**
     * Searches and returns a restriction by its id.
     * @param id The id of the wanted restriction.
     * @return A response entity with the found restriction as body or a not found response entity.
     */
    public RestrictionResponse findRestrictionById(Long id) {
        Restriction foundRestriction = restrictionDAO.findById(id);
        return restrictionResponseMapper.toResponse(foundRestriction);
    }

    /**
     * Deletes the restriction of the given id.
     * @param id The restriction's id to be deleted.
     * @return A response entity  with the deleted restriction as body or just a not found code.
     */
    public RestrictionResponse deleteRestriction(Long id){
        Restriction foundRestriction = restrictionDAO.findById(id);
        restrictionDAO.deleteById(id);
        return restrictionResponseMapper.toResponse(foundRestriction);
    }
}
