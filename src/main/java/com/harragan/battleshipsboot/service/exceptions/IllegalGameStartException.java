package com.harragan.battleshipsboot.service.exceptions;

public class IllegalGameStartException extends RuntimeException {
    public IllegalGameStartException(String message) {
        super(message);
    }
}
