package com.harragan.battleshipsboot.service;

import com.harragan.battleshipsboot.model.kotlinmodel.game.BoardPosition;
import org.springframework.stereotype.Service;

@Service
public class BoardPositionFactory {

    public static BoardPosition createBoardPosition(final char col, final int row) {
        if (!Character.isAlphabetic(col)) {
            throw new IllegalArgumentException("The column has to be a alphabetic character.");
        }
        if (row < 1) {
            throw new IllegalArgumentException("The row number needs to be greater than 1.");
        }
        return new BoardPosition(col, row);
    }
}
