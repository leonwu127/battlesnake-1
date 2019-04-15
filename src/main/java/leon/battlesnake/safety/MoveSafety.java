package leon.battlesnake.safety;

import leon.battlesnake.model.BattleSnakeRequest;
import leon.battlesnake.model.domain.MOVE;

public interface MoveSafety {
    boolean isItSafe(MOVE move, BattleSnakeRequest battleSnakeRequest);
}
