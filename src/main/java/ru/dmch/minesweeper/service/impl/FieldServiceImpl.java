package ru.dmch.minesweeper.service.impl;

import org.springframework.stereotype.Service;
import ru.dmch.minesweeper.service.FieldService;

import java.util.Arrays;
import java.util.Random;

@Service
public class FieldServiceImpl implements FieldService {

    @Override
    public String[][] createField(int width, int height, int mines_count) {
        String[][] field = new String[width][height];
        insertSpaces(field);
        insertBombs(field, mines_count);
        countBombs(field);
        return field;
    }

    private void countBombs(String[][] field) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {

                if (field[i][j].equals("X")) {
                    try {
                        field[i - 1][j - 1] = incrementString(field[i - 1][j - 1]);
                    } catch (ArrayIndexOutOfBoundsException ignored) {
                    }
                    try {
                        field[i][j - 1] = incrementString(field[i][j - 1]);
                    } catch (ArrayIndexOutOfBoundsException ignored) {
                    }
                    try {
                        field[i + 1][j - 1] = incrementString(field[i + 1][j - 1]);
                    } catch (ArrayIndexOutOfBoundsException ignored) {
                    }
                    try {
                        field[i + 1][j] = incrementString(field[i + 1][j]);
                    } catch (ArrayIndexOutOfBoundsException ignored) {
                    }
                    try {
                        field[i + 1][j + 1] = incrementString(field[i + 1][j + 1]);
                    } catch (ArrayIndexOutOfBoundsException ignored) {
                    }
                    try {
                        field[i][j + 1] = incrementString(field[i][j + 1]);
                    } catch (ArrayIndexOutOfBoundsException ignored) {
                    }
                    try {
                        field[i - 1][j + 1] = incrementString(field[i - 1][j + 1]);
                    } catch (ArrayIndexOutOfBoundsException ignored) {
                    }
                    try {
                        field[i - 1][j] = incrementString(field[i - 1][j]);
                    } catch (ArrayIndexOutOfBoundsException ignored) {
                    }
                }
            }
        }
    }

    private String incrementString(String bombs) {
        if (bombs.equals(" ")) {
            return "1";
        }
        if (bombs.equals("X")) {
            return "X";
        }
        int count = Integer.parseInt(bombs);
        count++;
        return Integer.toString(count);
    }

    private void insertBombs(String[][] field, int minesCount) {
        int bombsInRow = minesCount / field.length;
        int bombsRemainder = minesCount % field.length;

        if (bombsInRow > 0) {
            for (int i = 0; i < field.length; i++) {
                for (int j = 0; j < bombsInRow; j++) {
                    int random = getRandom(field[i].length);
                    if (field[i][random].equals("X")) {
                        if (!(i == 0)) {
                            i--;
                        }
                    } else {
                        field[i][random] = "X";
                    }
                }
            }
        }
        if (bombsRemainder > 0) {
            for (int i = 0; i < bombsRemainder; i++) {
                int randomWidth = getRandom(field.length);
                int randomLength = getRandom(field[0].length);
                if (field[randomWidth][randomLength].equals("X")) {
                    if (!(i == 0)) {
                        i--;
                    }
                } else {
                    field[randomWidth][randomLength] = "X";
                }
            }
        }
    }

    private void insertSpaces(String[][] field) {
        for (int i = 0; i < field.length; i++) {
            Arrays.fill(field[i], " ");
        }
    }

    private int getRandom(int length) {
        Random random = new Random();
        return random.nextInt(length);
    }
}
