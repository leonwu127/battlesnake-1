package leon.battlesnake.model;

import leon.battlesnake.model.domain.MOVE;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MoveResponse {
    private MOVE move;
}
