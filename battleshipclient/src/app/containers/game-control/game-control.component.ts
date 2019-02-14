import {Component, OnInit} from '@angular/core';
import {GameService} from '../../services/game.service';
import {PlayerService} from '../../services/player.service';
import {CreateGameRequest} from '../../models/create-game-request';
import {GridState} from '../../store/grid/grid.reducers';
import {Store} from '@ngrx/store';
import {InitialiseGridAction} from '../../store/grid/grid.actions';
import {PollingService} from '../../services/polling.service';
import {CreateGameRequestAction, PlayersToPlayersReadyPollAction} from '../../store/game/game.actions';
import {Observable} from 'rxjs';
import {AppState} from '../../store';

@Component({
  selector: 'app-game-control',
  templateUrl: './game-control.component.html',
  styleUrls: ['./game-control.component.scss']
})
export class GameControlComponent implements OnInit {

  private gameService: GameService;
  private createPlayerService: PlayerService;
  private store: Store<AppState>;
  private pollingService: PollingService;
  private playersReady$: Observable<number>;
  private playersInGame$: Observable<number>;
  private gameId$: Observable<number>;
  private gameId: number;

  constructor(gameService: GameService, createPlayerService: PlayerService, store: Store<AppState>, pollingService: PollingService) {
    this.gameService = gameService;
    this.createPlayerService = createPlayerService;
    this.store = store;
    this.pollingService = pollingService;
  }

  showGameCreationMenu = false;
  showPlayerCreationMenu = false;
  showJoinGameMenu = false;
  showShipPlacerMenu = false;
  showGrid = false;



  ngOnInit() {
    this.gameId$ = this.store.select(state => state.gameState.gameId);
    this.playersInGame$ = this.store.select(state => state.gameState.playersInGame);
    this.playersReady$ = this.store.select(state => state.gameState.playersReady);
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
    this.store.dispatch(new CreateGameRequestAction(createGameRequest));
    this.gameId$.subscribe(state => this.gameId = state);
  }

  createPlayer(playerName: string) {
    this.createPlayerService.createPlayer(this.gameId, playerName);
    this.showShipPlacerMenu = true;
    this.showGrid = true;
  }

  pollForPlayersToPlayersReady() {
    this.store.dispatch(new PlayersToPlayersReadyPollAction(this.gameId));
  }
}
