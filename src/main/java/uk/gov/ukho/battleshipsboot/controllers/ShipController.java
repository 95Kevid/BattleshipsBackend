package uk.gov.ukho.battleshipsboot.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.gov.ukho.battleshipsboot.facades.ShipPlacingFacade;
import uk.gov.ukho.battleshipsboot.model.game.BoardPosition;
import uk.gov.ukho.battleshipsboot.model.game.Orientation;
import uk.gov.ukho.battleshipsboot.model.ships.Destroyer;
import uk.gov.ukho.battleshipsboot.model.ships.Ship;


@RestController
public class ShipController {

    @Autowired
    private ShipPlacingFacade shipPlacingFacade;

    @RequestMapping(value = "/placedestoyer", method = RequestMethod.POST)
    public ResponseEntity<ShipPlaceRequestWrapper> placeDestroyer(
            @RequestBody ShipPlaceRequestWrapper shipPlaceRequestWrapper)
    {
        Destroyer destoyer = new Destroyer(shipPlaceRequestWrapper.getOrientation()
                , shipPlaceRequestWrapper.getBoardPosition());
        shipPlacingFacade.placeShip(shipPlaceRequestWrapper.getPlayerId()
                , shipPlaceRequestWrapper.getGameId(), destoyer);
        System.out.println("End point hit!");
        return new ResponseEntity<>(shipPlaceRequestWrapper,HttpStatus.CREATED);
    }

}
