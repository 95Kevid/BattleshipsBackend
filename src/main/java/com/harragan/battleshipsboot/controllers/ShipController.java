package com.harragan.battleshipsboot.controllers;
import com.harragan.battleshipsboot.facades.ShipPlacingFacade;
import com.harragan.battleshipsboot.model.ships.*;
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

    @RequestMapping(value = "/placecarrier", method = RequestMethod.POST)
    public ResponseEntity<ShipPlaceRequestWrapper> placeCarrier(
            @RequestBody ShipPlaceRequestWrapper shipPlaceRequestWrapper)
    {
        Carrier carrier = new Carrier(shipPlaceRequestWrapper.getOrientation()
                , shipPlaceRequestWrapper.getBoardPosition());
        shipPlacingFacade.placeShip(shipPlaceRequestWrapper.getPlayerId()
                , shipPlaceRequestWrapper.getGameId(), carrier);
        System.out.println("End point hit!");
        return new ResponseEntity<>(shipPlaceRequestWrapper,HttpStatus.CREATED);
    }

    @RequestMapping(value = "/placesubmarine", method = RequestMethod.POST)
    public ResponseEntity<ShipPlaceRequestWrapper> placeSubmarine(
            @RequestBody ShipPlaceRequestWrapper shipPlaceRequestWrapper)
    {
        Submarine submarine = new Submarine(shipPlaceRequestWrapper.getOrientation()
                , shipPlaceRequestWrapper.getBoardPosition());
        shipPlacingFacade.placeShip(shipPlaceRequestWrapper.getPlayerId()
                , shipPlaceRequestWrapper.getGameId(), submarine);
        System.out.println("End point hit!");
        return new ResponseEntity<>(shipPlaceRequestWrapper,HttpStatus.CREATED);
    }

    @RequestMapping(value = "/placecruiser", method = RequestMethod.POST)
    public ResponseEntity<ShipPlaceRequestWrapper> placeCruiser(
            @RequestBody ShipPlaceRequestWrapper shipPlaceRequestWrapper)
    {
        Cruiser cruiser = new Cruiser(shipPlaceRequestWrapper.getOrientation()
                , shipPlaceRequestWrapper.getBoardPosition());
        shipPlacingFacade.placeShip(shipPlaceRequestWrapper.getPlayerId()
                , shipPlaceRequestWrapper.getGameId(), cruiser);
        System.out.println("End point hit!");
        return new ResponseEntity<>(shipPlaceRequestWrapper,HttpStatus.CREATED);
    }

    @RequestMapping(value = "/placebattleship", method = RequestMethod.POST)
    public ResponseEntity<ShipPlaceRequestWrapper> placeBattleship(
            @RequestBody ShipPlaceRequestWrapper shipPlaceRequestWrapper)
    {
        Battleship battleship = new Battleship(shipPlaceRequestWrapper.getOrientation()
                , shipPlaceRequestWrapper.getBoardPosition());
        shipPlacingFacade.placeShip(shipPlaceRequestWrapper.getPlayerId()
                , shipPlaceRequestWrapper.getGameId(), battleship);
        System.out.println("End point hit!");
        return new ResponseEntity<>(shipPlaceRequestWrapper,HttpStatus.CREATED);
    }
}
