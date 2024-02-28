package ru.dmch.minesweeper.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dmch.minesweeper.entity.Game;
import ru.dmch.minesweeper.entity.request.GameTurnRequest;
import ru.dmch.minesweeper.entity.request.NewGameRequest;
import ru.dmch.minesweeper.repository.GamesRepository;
import ru.dmch.minesweeper.service.FieldService;
import ru.dmch.minesweeper.service.GameService;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class GameServiceImpl implements GameService {
    private final FieldService fieldService;
    private final GamesRepository gamesRepository;

    @Override
    public Game safeNewGame(NewGameRequest newGameRequest) {
        Game game = Game.builder()
                .game_id(UUID.randomUUID())
                .width(newGameRequest.getWidth())
                .height(newGameRequest.getHeight())
                .mines_count(newGameRequest.getMines_count())
                .field(fieldService.createField(
                        newGameRequest.getWidth(),
                        newGameRequest.getHeight(),
                        newGameRequest.getMines_count()
                ))
                .completed(false)
                .build();
        gamesRepository.safe(game);
        return game;
    }

    @Override
    public boolean checkNewGameRequest(NewGameRequest newGameRequest) {
        return newGameRequest.getMines_count() <= ((newGameRequest.getWidth() * newGameRequest.getHeight()) - 1)
                && (newGameRequest.getHeight() <= 30 && newGameRequest.getWidth() <= 30)
                && (newGameRequest.getHeight() > 0 && newGameRequest.getWidth() > 0);
    }

    @Override
    public Optional<Game> findGame(UUID uuid) {
        return gamesRepository.find(uuid);
    }

    @Override
    public boolean checkTurnRequest(GameTurnRequest gameTurnRequest, Game game) {
        return gameTurnRequest.getCol()>0 && gameTurnRequest.getCol()<=game.getWidth()
        && gameTurnRequest.getRow()>0 && gameTurnRequest.getRow()<=game.getHeight();
    }

    @Override
    public Game checkBombPosition(GameTurnRequest gameTurnRequest) {
        Game gameToCheck = gamesRepository.find(gameTurnRequest.getGame_id()).get();
        if(gameToCheck.getField()[gameTurnRequest.getCol()][gameTurnRequest.getRow()].equals("X")){
            gameToCheck.setCompleted(true);
            return gameToCheck;
        }
        return gameToCheck;
    }
}
