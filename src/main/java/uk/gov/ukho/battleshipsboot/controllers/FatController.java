package uk.gov.ukho.battleshipsboot.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.ukho.battleshipsboot.model.game.Game;
import uk.gov.ukho.battleshipsboot.model.game.Player;
import uk.gov.ukho.battleshipsboot.service.GameService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FatController {

    @Autowired
    private GameService gameService;

    @GetMapping("/test")
    public void createGame() {


    }


}
