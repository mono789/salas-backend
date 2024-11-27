package co.edu.udea.salasinfo.controller.v1;

import co.edu.udea.salasinfo.dto.request.RestrictionRequest;
import co.edu.udea.salasinfo.dto.response.RestrictionResponse;
import co.edu.udea.salasinfo.service.RestrictionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/restrictions")
@RequiredArgsConstructor
public class RestrictionController {

    private final RestrictionService restrictionService;


    @GetMapping
    public ResponseEntity<List<RestrictionResponse>> findAll() {
        return ResponseEntity.ok(restrictionService.findAllRestrictions());
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<RestrictionResponse> save(@RequestBody @Valid RestrictionRequest restrictionRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(restrictionService.createRestriction(restrictionRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestrictionResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(restrictionService.findRestrictionById(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<RestrictionResponse> remove(@PathVariable Long id) {
        return ResponseEntity.ok(restrictionService.deleteRestriction(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<RestrictionResponse> update(@PathVariable Long id, @RequestBody @Valid RestrictionRequest restrictionRequest) {
        return ResponseEntity.ok(restrictionService.updateRestriction(id, restrictionRequest));
    }
}

