import { Component, OnInit } from '@angular/core';
import {GameService} from '../../services/game.service';
import {PlayerService} from '../../services/player.service';
import {CreateGameRequest} from '../../models/create-game-request';
import {GridState} from '../../store/grid/grid.reducers';
import {Store} from '@ngrx/store';
import {InitialiseGridAction} from '../../store/grid/grid.actions';
import {Observable} from 'rxjs';
import {PollingService} from '../../services/polling.service';

@Component({
  selector: 'app-game-control',
  templateUrl: './game-control.component.html',
  styleUrls: ['./game-control.component.scss']
})
export class GameControlComponent implements OnInit {

  private gameService: GameService;
  private createPlayerService: PlayerService;
  private store: Store<GridState>;
  private gameStartPolling$: Observable<PlayersToPlayersReady>;

  constructor(gameService: GameService, createPlayerService: PlayerService, store: Store<GridState>, pollingService: PollingService) {
    this.gameService = gameService;
    this.createPlayerService = createPlayerService;
    this.store = store;
    this.gameStartPolling$ = pollingService.pollToStartGame();
  }

  showGameCreationMenu = false;
  showPlayerCreationMenu = false;
  showJoinGameMenu = false;
  showShipPlacerMenu = false;
  showGrid = false;

  ngOnInit() {

  }

  createGameButtonClicked() {
    this.showGameCreationMenu = true;
  }

  joinGameButtonClicked() {
    this.showJoinGameMenu = true;
    this.showPlayerCreationMenu = true;
  }

  createGame(createGameRequest: CreateGameRequest) {
    console.log('create game called with ' + createGameRequest.numberOfPlayers);
    this.showPlayerCreationMenu = true;
    this.store.dispatch(new InitialiseGridAction(createGameRequest.gridSize));
    return this.gameService.createGame(createGameRequest);
  }

  createPlayer(playerName: string)  {
    this.createPlayerService.createPlayer(this.gameService.gameId, playerName);
    this.showShipPlacerMenu = true;
    this.showGrid = true;
  }

  getGameId(): number {
    return this.gameService.gameId;
  }
}
