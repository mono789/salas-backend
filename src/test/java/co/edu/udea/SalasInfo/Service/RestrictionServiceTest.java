package co.edu.udea.SalasInfo.Service;

import co.edu.udea.SalasInfo.DAO.RestrictionRepository;
import co.edu.udea.SalasInfo.Model.Restriction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class RestrictionServiceTest {

    @Mock
    RestrictionRepository restrictionRepository;
    @InjectMocks
    RestrictionService restrictionService;
    private Restriction restriction;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        restriction = new Restriction(1, "No Gatos, ni perros");
    }

    @Test
    void findAllRestrictions() {
        List<Restriction> allRestrictions = Collections.singletonList(restriction);
        Mockito.when(restrictionRepository.findAll()).thenReturn(allRestrictions);
        List<Restriction> retrievedRestrictions = restrictionService
                .findAllRestrictions().getBody();
        assertNotNull(retrievedRestrictions);
        assertEquals(allRestrictions, retrievedRestrictions);
    }

    @Test
    void createRestriction() {
        Mockito.when(restrictionRepository.save(restriction)).thenReturn(restriction);
        Restriction savedRestriction = restrictionService.createRestriction(restriction).getBody();
        assertNotNull(savedRestriction);
        Mockito.verify(restrictionRepository).save(restriction);
    }

    @Test
    void findRestrictionById() {
        Mockito.when(restrictionRepository.findById(1)).thenReturn(Optional.ofNullable(restriction));
        assertNotNull(restrictionService.findRestrictionById(1).getBody());
    }

    @Test
    void deleteRestriction() {
        Mockito.when(restrictionRepository.findById(1)).thenReturn(Optional.of(restriction));
        restrictionService.deleteRestriction(1);
        Mockito.verify(restrictionRepository).deleteById(1);
        assertEquals(restriction, restrictionService.deleteRestriction(1).getBody());
    }
}
