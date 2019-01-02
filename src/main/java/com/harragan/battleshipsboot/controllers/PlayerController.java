package com.harragan.battleshipsboot.controllers;

import com.harragan.battleshipsboot.facades.PlayerAddingFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class PlayerController {

    @Autowired
    private PlayerAddingFacade playerAddingFacade;

    @RequestMapping(value = "/addplayer", method = POST)
    public ResponseEntity<Integer> createPlayer(@RequestBody PlayerRequestWrapper playerRequestWrapper) {
        int playerId = playerAddingFacade.createPlayerAndJoinToGame(playerRequestWrapper.getPlayerName()
                , playerRequestWrapper.getGameId());
        return new ResponseEntity<>(playerId, HttpStatus.CREATED);
    }
}
