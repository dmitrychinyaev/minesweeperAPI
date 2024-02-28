package ru.dmch.minesweeper.entity.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;
@Getter
@AllArgsConstructor
public class GameTurnRequest {
    private UUID game_id;
    private int col;
    private int row;
}
