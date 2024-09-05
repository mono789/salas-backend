package co.edu.udea.salasinfo.dto.response.room;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RoomApplicationResponse {
    private Integer applicationId;
    private String applicationName;
    private String version;
}
