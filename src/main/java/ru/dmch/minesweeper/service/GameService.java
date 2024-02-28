package ru.dmch.minesweeper.service;

import ru.dmch.minesweeper.entity.Game;
import ru.dmch.minesweeper.entity.request.GameTurnRequest;
import ru.dmch.minesweeper.entity.request.NewGameRequest;

import java.util.Optional;
import java.util.UUID;

public interface GameService {
    Game safeNewGame(NewGameRequest newGameRequest);
    boolean checkNewGameRequest(NewGameRequest newGameRequest);
    Optional<Game> findGame(UUID uuid);
    boolean checkTurnRequest(GameTurnRequest gameTurnRequest, Game game);

    Game checkBombPosition(GameTurnRequest gameTurnRequest);
}
