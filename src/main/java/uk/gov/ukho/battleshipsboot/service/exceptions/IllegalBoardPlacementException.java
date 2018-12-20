package uk.gov.ukho.battleshipsboot.service.exceptions;

public class IllegalBoardPlacementException extends RuntimeException{
    public IllegalBoardPlacementException(String message) {
        super(message);
    }
}
