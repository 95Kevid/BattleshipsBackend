package com.harragan.battleshipsboot.controllers;

import com.harragan.battleshipsboot.facades.PollingFacade;
import com.harragan.battleshipsboot.model.game.PlayersToPlayersReady;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PollingController {

    @Autowired
    private PollingFacade pollingFacade;

    @RequestMapping(value = "/waitingplayerpoll", method = RequestMethod.POST)
    public ResponseEntity<PlayersToPlayersReady> checkForWaitingPlayers(@RequestBody int gameId) {
        PlayersToPlayersReady playersToPlayersNotReady = pollingFacade
                .getNumberOfNotReadyPlayersToReadyPlayers(gameId);
       return new ResponseEntity<>(playersToPlayersNotReady, HttpStatus.OK);
    }
}
