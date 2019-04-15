package leon.battlesnake.move;

import org.springframework.stereotype.Component;
import leon.battlesnake.model.BattleSnakeRequest;
import leon.battlesnake.model.domain.MOVE;

import java.util.Random;

@Component
public class RandomMoveStrategy implements MoveStrategy {
    private Random random = new Random();

    @Override
    public String getName() {
        return "random";
    }

    @Override
    public MOVE move(BattleSnakeRequest moveRequest) {
        return MOVE.values()[random.nextInt(4)];
    }
}
