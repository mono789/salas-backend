package co.edu.udea.salasinfo.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RoomDTO {
    private Integer roomId;
    private Integer computerAmount;
    private String building;
    private String roomNum;
    private String roomName;
    private Integer subRoom;
    private List<ApplicationDTO> software;
    private List<RestrictionDTO> restrictions;
    private List<ImplementDTO> implementList;

}
