package leon.battlesnake.safety;

import org.springframework.stereotype.Component;
import leon.battlesnake.model.BattleSnakeRequest;
import leon.battlesnake.model.domain.Body;
import leon.battlesnake.model.domain.MOVE;

@Component
public class WallSafety implements MoveSafety {
    @Override
    public boolean isItSafe(MOVE move, BattleSnakeRequest battleSnakeRequest) {
        Body head = battleSnakeRequest.getYou().getBody().get(0);

        switch (move) {
            case UP:
                return head.getY() != 0;
            case RIGHT:
                return head.getX() < battleSnakeRequest.getBoard().getWidth() - 1;
            case LEFT:
                return head.getX() != 0;
            case DOWN:
                return head.getY() < battleSnakeRequest.getBoard().getHeight() - 1;
        }

        return false;
    }
}
