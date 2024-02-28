package ru.dmch.minesweeper.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class Game {
    private UUID game_id;
    private int width;
    private int height;
    private int mines_count;
    private boolean completed;
    private String[][] field;
}

