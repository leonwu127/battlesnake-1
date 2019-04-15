package leon.battlesnake.service;

import org.springframework.stereotype.Service;
import leon.battlesnake.StartService;
import leon.battlesnake.model.BattleSnakeRequest;
import leon.battlesnake.model.StartResponse;
import leon.battlesnake.model.domain.HEAD_TYPE;
import leon.battlesnake.model.domain.TAIL_TYPE;

@Service
public class RandomSnakeStartService implements StartService {

    private static final String RED = "#ff0000";
    private RandomStrategy randomStrategy;

    public RandomSnakeStartService(RandomStrategy randomStrategy) {
        this.randomStrategy = randomStrategy;
    }

    @Override
    public StartResponse start(BattleSnakeRequest startRequest) {
        return StartResponse.builder()
            .color(RED)
            .headType(HEAD_TYPE.values()[randomStrategy.getNextInt(HEAD_TYPE.values().length)])
            .tailType(TAIL_TYPE.values()[randomStrategy.getNextInt(TAIL_TYPE.values().length)])
            .build();
    }
}
