package leon.battlesnake.service;

import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import leon.battlesnake.model.BattleSnakeRequest;
import leon.battlesnake.model.MoveResponse;
import leon.battlesnake.model.domain.MOVE;
import leon.battlesnake.move.MoveStrategy;
import leon.battlesnake.move.MoveStrategyFactory;
import leon.battlesnake.safety.MoveSafety;
import leon.battlesnake.safety.SnakeSafety;
import leon.battlesnake.safety.WallSafety;
import leon.battlesnake.service.StrategyBasedMoveService;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static leon.battlesnake.SnakeHelper.*;

public class StrategyBasedMoveServiceUTest {

    private MoveStrategyFactory moveStrategyFactory = mock(MoveStrategyFactory.class);
    private MoveStrategy moveStrategy = mock(MoveStrategy.class);
    private List<MoveSafety> moveSafetyList = Lists.newArrayList(new WallSafety(), new SnakeSafety());
    private StrategyBasedMoveService strategyBasedMoveService = new StrategyBasedMoveService(moveStrategyFactory,
        moveSafetyList);

    @Before
    public void setup() {
        when(moveStrategyFactory.moveStrategy(anyString())).thenReturn(moveStrategy);
        when(moveStrategyFactory.fallBackMoveStrategy()).thenReturn(moveStrategy);
        when(moveStrategy.move(any(BattleSnakeRequest.class))).thenReturn(MOVE.UP);
    }

    @Test
    public void testCanMoveUpWhenSafe() {
        MoveResponse actualMove = strategyBasedMoveService.move(
            battleSnakeRequest(snake(body(5, 5)))
        );

        assertThat(actualMove.getMove()).isEqualTo(MOVE.UP);
    }

    @Test
    public void testCannotMoveUpWhenNotSafeInTopRightCorner() {
        MoveResponse actualMove = strategyBasedMoveService.move(
            battleSnakeRequest(snake(body(14, 0), body(13, 0)))
        );

        assertThat(actualMove.getMove()).isEqualTo(MOVE.DOWN);
    }

    @Test
    public void testCannotMoveUpWhenNotSafeBlockedByMySnake() {
        MoveResponse actualMove = strategyBasedMoveService.move(
            battleSnakeRequest(snake(body(0, 0), body(0, 1)))
        );

        assertThat(actualMove.getMove()).isEqualTo(MOVE.RIGHT);
    }

    @Test
    public void testCannotMoveUpWhenNotSafeBlockedByAnotherSnake() {
        MoveResponse actualMove = strategyBasedMoveService.move(
            battleSnakeRequest(snake(body(14, 14)), snake(body(14, 13)))
        );

        assertThat(actualMove.getMove()).isEqualTo(MOVE.LEFT);
    }

    @Test
    public void testMoveUpWhenNowhereIsSafe() {
        MoveResponse actualMove = strategyBasedMoveService.move(
            battleSnakeRequest(
                snake(body(14, 0)),
                snake(body(14, 1)),
                snake(body(13, 0))
            )
        );

        assertThat(actualMove.getMove()).isEqualTo(MOVE.RIGHT);
    }
}