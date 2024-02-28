package ru.dmch.minesweeper.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.dmch.minesweeper.entity.Game;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class GamesRepository {
    private final HashMap<UUID, Game> gamesHashMap;

    public void safe(Game game) {
        gamesHashMap.put(game.getGame_id(), game);
    }

    public Optional<Game> find(UUID uuid) {
        return Optional.ofNullable(gamesHashMap.get(uuid));
    }
}
