package com.harragan.battleshipsboot.controllers;

import com.harragan.battleshipsboot.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class GameController {

    @Autowired
    private GameService gameService;

    @RequestMapping(value = "/creategame/{numberOfPlayers}/{arenaSize}", method = RequestMethod.POST)
    public ResponseEntity<Integer> createGame(@PathVariable int numberOfPlayers, @PathVariable int arenaSize) {
        int gameId = gameService.createGame(numberOfPlayers, arenaSize);
        return new ResponseEntity<>(gameId, HttpStatus.CREATED);
    }
}
