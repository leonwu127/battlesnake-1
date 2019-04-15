package leon.battlesnake;

import leon.battlesnake.model.BattleSnakeRequest;
import leon.battlesnake.model.MoveResponse;
import leon.battlesnake.model.StartResponse;
import leon.battlesnake.model.domain.Snake;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/*
http://10.194.195.169:8080
 */
@Slf4j
@RestController
public class Controller {

    private StartService startService;
    private MoveService moveService;

    public Controller(StartService startService, MoveService moveService) {
        this.startService = startService;
        this.moveService = moveService;
    }

    @PostMapping("/start")
    public StartResponse start(@RequestBody BattleSnakeRequest startRequest) {
        return startService.start(startRequest);
    }

    @PostMapping("/move")
    public MoveResponse move(@RequestBody BattleSnakeRequest moveRequest) {
        Snake you = moveRequest.getYou();
        String name = you.getName();
        int turn = moveRequest.getTurn();
        log.info("{}-{}: [{}] {}", turn, name, you.getHealth(), you.getBody().toString());
        MoveResponse move = moveService.move(moveRequest);
        log.info("{}-{}: moved={}", turn, name, move.getMove());
        return move;
    }

    @PostMapping("/end")
    public void end(@RequestBody BattleSnakeRequest endRequest) {
        log.info("==========================Game Ended==========================");
    }

    @PostMapping("/ping")
    public void ping() {
    }
}
