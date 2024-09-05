package co.edu.udea.salasinfo.dto.request.filter;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RoomFilter {
    String implement;
    String restriction;
    String software;
    Integer computerAmount;
}
