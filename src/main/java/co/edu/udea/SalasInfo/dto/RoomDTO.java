package co.edu.udea.SalasInfo.dto;

import co.edu.udea.SalasInfo.model.Room;
import co.edu.udea.SalasInfo.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
