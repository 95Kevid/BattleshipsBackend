package uk.gov.ukho.battleshipsboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.gov.ukho.battleshipsboot.model.game.Game;
import uk.gov.ukho.battleshipsboot.service.GameService;

@RestController
public class GameController {

    @Autowired
    private GameService gameService;

    @RequestMapping(value = "/creategame/{numberOfPlayers}", method = RequestMethod.POST)
    public ResponseEntity<Integer> createGame(@PathVariable int numberOfPlayers) {
        int gameId = gameService.createGame(numberOfPlayers);
        return new ResponseEntity<>(gameId, HttpStatus.CREATED);
    }
}
