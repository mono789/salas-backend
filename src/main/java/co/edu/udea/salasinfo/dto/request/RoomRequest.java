package co.edu.udea.salasinfo.dto.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RoomRequest {
    private Integer computerAmount;
    private String building;
    private String roomNum;
    private String roomName;
    private Integer subRoom;
}
