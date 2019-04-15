package leon.battlesnake;


import leon.battlesnake.model.BattleSnakeRequest;
import leon.battlesnake.model.StartResponse;

public interface StartService {
    StartResponse start(BattleSnakeRequest startRequest);
}
