package co.edu.udea.salasinfo.dto.response;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ValidationExceptionResponse {
    private Integer statusCode;
    private HttpStatus status;
    private String message;
    private LocalDateTime timestamp;
    private List<String> errors;
}
