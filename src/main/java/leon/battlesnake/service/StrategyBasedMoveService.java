package leon.battlesnake.service;

import leon.battlesnake.move.MoveStrategy;
import leon.battlesnake.move.MoveStrategyFactory;
import leon.battlesnake.safety.MoveSafety;
import org.springframework.stereotype.Service;
import leon.battlesnake.MoveService;
import leon.battlesnake.model.BattleSnakeRequest;
import leon.battlesnake.model.MoveResponse;
import leon.battlesnake.model.domain.MOVE;

import java.util.ArrayList;
import java.util.List;

@Service
public class StrategyBasedMoveService implements MoveService {
    private MoveStrategyFactory moveStrategyFactory;
    private List<MoveSafety> moveSafetyList;

    public StrategyBasedMoveService(MoveStrategyFactory moveStrategyFactory, List<MoveSafety> moveSafetyList) {
        this.moveStrategyFactory = moveStrategyFactory;
        this.moveSafetyList = moveSafetyList;
    }

    @Override
    public MoveResponse move(BattleSnakeRequest moveRequest) {
        MoveStrategy moveStrategy = moveStrategyFactory.moveStrategy(moveRequest.getYou().getName());

        MOVE move = finalMove(moveStrategy.move(moveRequest), moveRequest);

        return MoveResponse.builder()
            .move(move)
            .build();
    }

    private MOVE finalMove(MOVE move, BattleSnakeRequest battleSnakeRequest) {
        List<MOVE> remainingMoves = new ArrayList<>(List.of(MOVE.values()));
        remainingMoves.remove(move);

        MOVE attemptMove = move;

        while (remainingMoves.size() > 0 && !isSafe(attemptMove, battleSnakeRequest)) {
            attemptMove = moveStrategyFactory.fallBackMoveStrategy().move(battleSnakeRequest);

            if (remainingMoves.contains(attemptMove)) {
                remainingMoves.remove(attemptMove);
            } else {
                attemptMove = remainingMoves.remove(0);
            }
        }

        return attemptMove;
    }

    private boolean isSafe(MOVE move, BattleSnakeRequest battleSnakeRequest) {
        for (MoveSafety moveSafety : moveSafetyList) {
            if (!moveSafety.isItSafe(move, battleSnakeRequest)) {
                return false;
            }
        }

        return true;
    }
}
