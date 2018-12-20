package com.harragan.battleshipsboot.controllers;
import com.harragan.battleshipsboot.facades.ShipPlacingFacade;
import com.harragan.battleshipsboot.model.ships.Destroyer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
