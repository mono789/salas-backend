package co.edu.udea.salasinfo.dto.response.room;

import co.edu.udea.salasinfo.utils.enums.ImplementCondition;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class RoomImplementResponse {
    private Long id;
    private Long roomId;
    private Long implementId;
    private ImplementCondition condition;
}
