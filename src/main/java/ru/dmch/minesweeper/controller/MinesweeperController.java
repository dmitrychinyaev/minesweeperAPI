package ru.dmch.minesweeper.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dmch.minesweeper.entity.Game;
import ru.dmch.minesweeper.entity.exception.GameException;
import ru.dmch.minesweeper.entity.request.GameTurnRequest;
import ru.dmch.minesweeper.entity.request.NewGameRequest;
import ru.dmch.minesweeper.entity.response.GameInfoResponse;
import ru.dmch.minesweeper.service.GameService;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MinesweeperController {
    private final GameService gameService;
    private static final String ERROR_MESSAGE = "Произошла непредвиденная ошибка";

    @PostMapping("/new")
    public ResponseEntity<GameInfoResponse> makeNewGame (@RequestBody NewGameRequest newGameRequest) {
        if (newGameRequest == null || !gameService.checkNewGameRequest(newGameRequest)) {
            throw new GameException(ERROR_MESSAGE);
        }
        GameInfoResponse gameInfoResponse = new GameInfoResponse(gameService.safeNewGame(newGameRequest));
        return ResponseEntity.ok(gameInfoResponse);
    }

    @PostMapping("/turn")
    public ResponseEntity<GameInfoResponse> makeTurn(@RequestBody GameTurnRequest gameTurnRequest){
        Optional<Game> optionalGame = gameService.findGame(gameTurnRequest.getGame_id());
        if(optionalGame.isEmpty() | !gameService.checkTurnRequest(gameTurnRequest, optionalGame.get())){
            throw new GameException(ERROR_MESSAGE);
        }
        GameInfoResponse gameInfoResponse = new GameInfoResponse(gameService.checkBombPosition(gameTurnRequest));
        return ResponseEntity.ok(gameInfoResponse);
    }
}
