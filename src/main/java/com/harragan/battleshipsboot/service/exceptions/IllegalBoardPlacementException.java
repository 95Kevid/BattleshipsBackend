package com.harragan.battleshipsboot.service.exceptions;

public class IllegalBoardPlacementException extends RuntimeException {
  public IllegalBoardPlacementException(String message) {
    super(message);
  }
}
