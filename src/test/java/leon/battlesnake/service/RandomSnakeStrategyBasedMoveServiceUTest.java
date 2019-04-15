package leon.battlesnake.service;

import org.junit.Test;
import leon.battlesnake.model.StartResponse;
import leon.battlesnake.model.domain.HEAD_TYPE;
import leon.battlesnake.model.domain.TAIL_TYPE;
import leon.battlesnake.service.RandomSnakeStartService;
import leon.battlesnake.service.RandomStrategy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RandomSnakeStrategyBasedMoveServiceUTest {

    private RandomStrategy randomStrategy = mock(RandomStrategy.class);
    private RandomSnakeStartService randomSnakeStartService = new RandomSnakeStartService(randomStrategy);

    @Test
    public void testStart() {
        when(randomStrategy.getNextInt(HEAD_TYPE.values().length)).thenReturn(1);
        when(randomStrategy.getNextInt(TAIL_TYPE.values().length)).thenReturn(2);

        StartResponse firstResponse = randomSnakeStartService.start(null);

        assertThat(firstResponse.getHeadType()).isEqualTo(HEAD_TYPE.BELUGA);
        assertThat(firstResponse.getTailType()).isEqualTo(TAIL_TYPE.BOLT);
        assertThat(firstResponse.getColor()).isEqualTo("#ff0000");
    }
}