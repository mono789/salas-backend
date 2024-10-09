package co.edu.udea.salasinfo.controller.v1;

import co.edu.udea.salasinfo.configuration.advisor.responses.ExceptionResponse;
import co.edu.udea.salasinfo.dto.request.user.UserRoleRequest;
import co.edu.udea.salasinfo.dto.response.user.UserResponse;
import co.edu.udea.salasinfo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "find all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List with all users", content = @Content(array = @ArraySchema(schema = @Schema(implementation = UserResponse.class)))),
    })
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @Operation(summary = "Find a user by their id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User has been found", content = @Content(schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable String id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @Operation(summary = "Update the role of a User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User role has changed", content = @Content(schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @PutMapping("/{id}/role")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<UserResponse> updateUserRole(@PathVariable String id, @RequestBody UserRoleRequest userRoleRequest) {
        return ResponseEntity.ok(userService.updateRole(id, userRoleRequest.getRoleName()));
    }
}
