package ru.dmch.minesweeper.entity.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class NewGameRequest {
    private int width;
    private int height;
    private int mines_count;
}
