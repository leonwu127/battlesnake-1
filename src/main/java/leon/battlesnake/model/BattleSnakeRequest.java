package leon.battlesnake.model;

import leon.battlesnake.model.domain.Board;
import leon.battlesnake.model.domain.Game;
import leon.battlesnake.model.domain.Snake;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BattleSnakeRequest {
    private Game game;
    private int turn;
    private Board board;
    private Snake you;
}
