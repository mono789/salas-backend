package co.edu.udea.salasinfo.dto.response.room;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RoomResponse {
    private Integer id;
    private Integer computerAmount;
    private String building;
    private String roomNum;
    private String roomName;
    private Integer subRoom;
}
