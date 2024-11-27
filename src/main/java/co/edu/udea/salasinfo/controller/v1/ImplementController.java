package co.edu.udea.salasinfo.controller.v1;

import co.edu.udea.salasinfo.configuration.advisor.responses.ExceptionResponse;
import co.edu.udea.salasinfo.dto.request.ImplementRequest;
import co.edu.udea.salasinfo.dto.response.ImplementResponse;
import co.edu.udea.salasinfo.service.ImplementService;
import co.edu.udea.salasinfo.utils.RestConstants;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/implements")
@RequiredArgsConstructor
public class ImplementController {

    private final ImplementService implementService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = RestConstants.CODE_OK, description = "Implements retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ImplementResponse.class)))
    })
    @GetMapping
    public ResponseEntity<List<ImplementResponse>> findAll() {
        return ResponseEntity.ok(implementService.findAll());
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = RestConstants.CODE_CREATED, description = "Implement created successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ImplementResponse.class)))
    })
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ImplementResponse> save(@RequestBody @Valid ImplementRequest implementRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(implementService.createImplement(implementRequest));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = RestConstants.CODE_OK, description = "Application found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ImplementResponse.class))),
            @ApiResponse(responseCode = RestConstants.CODE_NOT_FOUND, description = "Application not found",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<ImplementResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(implementService.findById(id));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = RestConstants.CODE_OK, description = "Application deleted successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ImplementResponse.class))),
            @ApiResponse(responseCode = RestConstants.CODE_NOT_FOUND, description = "Application not found",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ImplementResponse> remove(@PathVariable Long id) {
        return ResponseEntity.ok(implementService.deleteImplement(id));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = RestConstants.CODE_OK, description = "Application updated successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ImplementResponse.class))),
            @ApiResponse(responseCode = RestConstants.CODE_NOT_FOUND, description = "Application not found",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ImplementResponse> update(@PathVariable Long id, @RequestBody @Valid ImplementRequest implementRequest) {
        return ResponseEntity.ok(implementService.updateImplement(id, implementRequest));
    }
}
