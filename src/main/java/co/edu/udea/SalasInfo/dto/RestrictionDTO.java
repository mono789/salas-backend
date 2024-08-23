package co.edu.udea.SalasInfo.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RestrictionDTO {
    // Attributes
    private Integer restrictionId;
    private String description;
    private List<RoomDTO> rooms;
}
