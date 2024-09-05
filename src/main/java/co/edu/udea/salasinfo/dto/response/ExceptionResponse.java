package co.edu.udea.salasinfo.dto.response;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ExceptionResponse {
    private Integer statusCode;
    private HttpStatus status;
    private String message;
    private LocalDateTime timestamp;
}
