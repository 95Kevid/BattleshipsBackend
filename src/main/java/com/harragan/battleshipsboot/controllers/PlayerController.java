package com.harragan.battleshipsboot.controllers;

import com.harragan.battleshipsboot.facades.PlayerAddingFacade;
import com.harragan.battleshipsboot.model.game.Player;
import com.harragan.battleshipsboot.repositorys.PlayerRepository;
import com.harragan.battleshipsboot.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class PlayerController {

    @Autowired
    private PlayerAddingFacade playerAddingFacade;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private PlayerRepository playerRepository;

    @RequestMapping(value = "/addplayer", method = POST)
    public ResponseEntity<Integer> createPlayer(@RequestBody PlayerRequestWrapper playerRequestWrapper) {
        int playerId = playerAddingFacade.createPlayerAndJoinToGame(playerRequestWrapper.getPlayerName()
                , playerRequestWrapper.getGameId());
        return new ResponseEntity<>(playerId, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/readytostart/{playerNo}", method = POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void playerReady(@PathVariable int playerNo) {
        playerService.setPlayerIsReady(playerNo, playerRepository);
    }
}
