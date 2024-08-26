package co.edu.udea.salasinfo.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ImplementDTO {
    private Integer implementId;
    private String implementName;
    private String state;
    private List<RoomDTO> rooms;
}
