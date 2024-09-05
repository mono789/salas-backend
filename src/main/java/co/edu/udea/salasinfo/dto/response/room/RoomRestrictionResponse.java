package co.edu.udea.salasinfo.dto.response.room;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RoomRestrictionResponse {
    // Attributes
    private Integer restrictionId;
    private String description;
}
