package co.edu.udea.salasinfo.dto.response.room;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SpecificRoomResponse {
    private Integer roomId;
    private Integer computerAmount;
    private String building;
    private String roomNum;
    private String roomName;
    private Integer subRoom;
    private List<RoomApplicationResponse> software;
    private List<RoomRestrictionResponse> restrictions;
    private List<RoomImplementResponse> implementList;

}
