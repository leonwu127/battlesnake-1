package leon.battlesnake.move;

import leon.battlesnake.model.BattleSnakeRequest;
import leon.battlesnake.model.domain.MOVE;

public interface MoveStrategy {
    String getName();

    MOVE move(BattleSnakeRequest moveRequest);
}
