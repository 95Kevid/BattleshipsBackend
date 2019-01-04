package com.harragan.battleshipsboot.controllers;

import com.harragan.battleshipsboot.facades.PollingFacade;
import com.harragan.battleshipsboot.model.game.PlayersToPlayersNotReady;
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

    @RequestMapping(value = "/waitingPlayerPoll", method = RequestMethod.GET)
    public ResponseEntity<PlayersToPlayersNotReady> checkForWaitingPlayers(@RequestBody int gameId) {
        PlayersToPlayersNotReady playersToPlayersNotReady = pollingFacade
                .getNumberOfNotReadyPlayersToReadyPlayers(gameId);
       return new ResponseEntity<>(playersToPlayersNotReady, HttpStatus.OK);
    }
}
