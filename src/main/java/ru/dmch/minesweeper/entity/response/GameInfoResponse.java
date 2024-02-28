package ru.dmch.minesweeper.entity.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import ru.dmch.minesweeper.entity.Game;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class GameInfoResponse {
    private Game game;
}
