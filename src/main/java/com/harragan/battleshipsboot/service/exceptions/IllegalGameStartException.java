package com.harragan.battleshipsboot.service.exceptions;

public class IllegalGameStartException extends RuntimeException {
    public IllegalGameStartException(final String message) {
        super(message);
    }
}
