import { Component, OnInit } from '@angular/core';
import {CreateGameService} from '../../services/create-game.service';
import {CreatePlayerService} from '../../services/create-player.service';

@Component({
  selector: 'app-game-control',
  templateUrl: './game-control.component.html',
  styleUrls: ['./game-control.component.scss']
})
export class GameControlComponent implements OnInit {

  private createGameService: CreateGameService;
  private createPlayerService: CreatePlayerService;

  constructor(createGameService: CreateGameService, createPlayerService: CreatePlayerService) {
    this.createGameService = createGameService;
    this.createPlayerService = createPlayerService;
  }

  showGameCreationMenu = false;
  showPlayerCreationMenu = false;
  showJoinGameMenu = false;
  gameId: number;
  playerId: number;

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
    return this.createGameService.createGame(numberOfPlayers).subscribe((result) => this.gameId = result);
  }

  createPlayer(playerName: string)  {
    this.createPlayerService.createPlayer(this.gameId, playerName).subscribe((result) => {
      this.playerId = result;
      console.log(result);
    });
  }

}
