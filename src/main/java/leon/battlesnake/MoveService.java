package leon.battlesnake;


import leon.battlesnake.model.BattleSnakeRequest;
import leon.battlesnake.model.MoveResponse;

public interface MoveService {
    MoveResponse move(BattleSnakeRequest moveRequest);
}
