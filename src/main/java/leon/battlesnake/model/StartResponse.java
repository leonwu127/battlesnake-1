package leon.battlesnake.model;

import leon.battlesnake.model.domain.HEAD_TYPE;
import leon.battlesnake.model.domain.TAIL_TYPE;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StartResponse {
    //#ff0000
    private String color;
    private HEAD_TYPE headType;
    private TAIL_TYPE tailType;
}
