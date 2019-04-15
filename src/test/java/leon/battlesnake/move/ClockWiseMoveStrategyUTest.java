package leon.battlesnake.move;

import org.junit.Test;
import leon.battlesnake.model.domain.MOVE;
import leon.battlesnake.move.ClockWiseMoveStrategy;

import static org.assertj.core.api.Assertions.assertThat;
import static leon.battlesnake.SnakeHelper.*;
import static leon.battlesnake.model.domain.MOVE.*;

public class ClockWiseMoveStrategyUTest {

    private ClockWiseMoveStrategy clockWiseMoveStrategy = new ClockWiseMoveStrategy();

    @Test
    public void in0_0Corner() {
        MOVE newMove =
            clockWiseMoveStrategy.move(battleSnakeRequest(
                snake(body(0, 0), body(1, 0))
            ));
        assertThat(newMove).isEqualTo(UP);
    }

    @Test
    public void in15_0Corner() {
        MOVE newMove =
            clockWiseMoveStrategy.move(battleSnakeRequest(
                snake(body(14, 0), body(14, 1))
            ));
        assertThat(newMove).isEqualTo(RIGHT);
    }

    @Test
    public void in15_15Corner() {
        MOVE newMove =
            clockWiseMoveStrategy.move(battleSnakeRequest(
                snake(body(14, 14), body(13, 14))
            ));
        assertThat(newMove).isEqualTo(DOWN);
    }

    @Test
    public void in0_15Corner() {
        MOVE newMove =
            clockWiseMoveStrategy.move(battleSnakeRequest(
                snake(body(0, 14), body(0, 13))
            ));
        assertThat(newMove).isEqualTo(LEFT);
    }
}

