package uk.gov.ukho.battleshipsboot.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.ukho.battleshipsboot.facades.ShipPlacingFacade;
import uk.gov.ukho.battleshipsboot.model.game.BoardPosition;
import uk.gov.ukho.battleshipsboot.model.game.Orientation;
import uk.gov.ukho.battleshipsboot.model.ships.Destroyer;



@RestController
public class ShipController {

    @Autowired
    private ShipPlacingFacade shipPlacingFacade;

    @RequestMapping(value = "/placedestoyer/{playerId}/{gameId}/{position]", method = RequestMethod.POST)
    public void placeDestroyer(@PathVariable int playerId, @PathVariable int gameId, @PathVariable BoardPosition position
                               , @PathVariable Orientation orientation) {
        Destroyer destoyer = new Destroyer(orientation, position);
        shipPlacingFacade.placeShip(playerId, gameId, destoyer);
    }

}
