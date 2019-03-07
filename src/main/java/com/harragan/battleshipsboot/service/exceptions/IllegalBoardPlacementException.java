package com.harragan.battleshipsboot.service.exceptions;

public class IllegalBoardPlacementException extends RuntimeException {
    public IllegalBoardPlacementException(final String message) {
        super(message);
    }
}
