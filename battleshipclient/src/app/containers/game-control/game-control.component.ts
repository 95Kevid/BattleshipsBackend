import { Component, OnInit } from '@angular/core';
import {GameService} from '../../services/game.service';
import {PlayerService} from '../../services/player.service';

@Component({
  selector: 'app-game-control',
  templateUrl: './game-control.component.html',
  styleUrls: ['./game-control.component.scss']
})
export class GameControlComponent implements OnInit {

  private gameService: GameService;
  private createPlayerService: PlayerService;

  constructor(gameService: GameService, createPlayerService: PlayerService) {
    this.gameService = gameService;
    this.createPlayerService = createPlayerService;
  }

  showGameCreationMenu = false;
  showPlayerCreationMenu = false;
  showJoinGameMenu = false;

  ngOnInit() {
  }

  createGameButtonClicked() {
    this.showGameCreationMenu = true;
  }

  joinGameButtonClicked() {
    this.showJoinGameMenu = true;
    this.showPlayerCreationMenu = true;
  }

  createGame(numberOfPlayers: number) {
    console.log('create game called with ' + numberOfPlayers);
    this.showPlayerCreationMenu = true;
    return this.gameService.createGame(numberOfPlayers);
  }

  createPlayer(playerName: string)  {
    this.createPlayerService.createPlayer(this.gameService.gameId, playerName);
  }

  getGameId(): number {
    return this.gameService.gameId;
  }

}
