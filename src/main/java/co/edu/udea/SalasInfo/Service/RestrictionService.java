package co.edu.udea.SalasInfo.Service;

import co.edu.udea.SalasInfo.DAO.RestrictionRepository;
import co.edu.udea.SalasInfo.Model.Restriction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestrictionService {

    @Autowired
    RestrictionRepository restrictionRepository;

    /**
     * Retrieves all existent room restrictions.
     * @return A response Entity with the Found Restrictions as body.
     */
    public ResponseEntity<List<Restriction>> findAllRestrictions(){
        return ResponseEntity.ok(restrictionRepository.findAll());
    }

    /**
     * Saves in database the give Restriction if there is not another with the same description.
     * @param restriction The restriction to save.
     * @return A response entity with the saved restriction or a bad request.
     */
    public ResponseEntity<Restriction> createRestriction(Restriction restriction){
        Optional<Restriction> optionalRestriction = restrictionRepository
                .findByDescription(restriction.getDescription());
        // Return a bad request if exist a restriction with that description or the created description
        return optionalRestriction.isPresent() ?
                ResponseEntity.badRequest().build() :
                ResponseEntity.ok(restrictionRepository.save(restriction));
    }

    /**
     * Searches and returns a restriction by its id.
     * @param id The id of the wanted restriction.
     * @return A response entity with the found restriction as body or a not found response entity.
     */
    public ResponseEntity<Restriction> findRestrictionById(Integer id) {
        Optional<Restriction> optionalRestriction = restrictionRepository.findById(id);
        return optionalRestriction.map(ResponseEntity::ok).orElseGet(() ->
                ResponseEntity.notFound().build()
        );
    }

    /**
     * Update a existent restriction with the given Restriction Parameters.
     * @param restriction An object which contains the information to update.
     * @return A response entity with the updated restriction or a not found code.
     */
    public ResponseEntity<Restriction> updateRestriction(Restriction restriction){
        Optional<Restriction> optionalRestriction = restrictionRepository.findById(restriction.getRestrictionId());
        return optionalRestriction.isEmpty() ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok(restrictionRepository.save(restriction));
    }

    /**
     * Deletes the restriction of the given id.
     * @param id The restriction's id to be deleted.
     * @return A response entity  with the deleted restriction as body or just a not found code.
     */
    public ResponseEntity<Restriction> deleteRestriction(Integer id){
        Optional<Restriction> optionalRestriction = restrictionRepository.findById(id);
        if(optionalRestriction.isEmpty()) return ResponseEntity.notFound().build();
        restrictionRepository.deleteById(id);
        return ResponseEntity.ok(optionalRestriction.get());
    }
}
