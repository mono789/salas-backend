package co.edu.udea.salasinfo.dto.response.room;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RoomImplementResponse {
    private Integer implementId;
    private String implementName;
    private String state;
}
