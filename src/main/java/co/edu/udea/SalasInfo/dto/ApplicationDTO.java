package co.edu.udea.SalasInfo.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ApplicationDTO {
    private Integer applicationId;
    private String applicationName;
    private String version;
    private List<RoomDTO> rooms;
}
