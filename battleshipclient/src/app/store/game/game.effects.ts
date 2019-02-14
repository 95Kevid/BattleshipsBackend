import {Injectable} from '@angular/core';
import {GameService} from '../../services/game.service';
import {Effect, ofType, Actions} from '@ngrx/effects';
import {
  CreateGameRequestAction,
  GameCreatedAction,
  PlayersToPlayersReadyPollAction,
  PlayersToPlayersReadyPollSuccessAction
} from './game.actions';
import {map, switchMap} from 'rxjs/operators';
import {PollingService} from '../../services/polling.service';


@Injectable()
export class GameEffects {
  constructor(private actions$: Actions, private gameService: GameService, private pollingService: PollingService) {}

 @Effect()
 public requestPlayersToPlayersReady$ = this.actions$.pipe(
   ofType<PlayersToPlayersReadyPollAction>('PLAYERS_TO_PLAYERS_READY_REQUEST'),
   switchMap(action => this.pollingService.pollToStartGame(action.payload)),
   map(playersToPlayersReady => new PlayersToPlayersReadyPollSuccessAction(playersToPlayersReady))
 )

 @Effect()
 public createGameRequest$ = this.actions$.pipe(
   ofType<CreateGameRequestAction>('CREATE_GAME'),
   map(action => {
     console.log('Game request action has hit effect');
     return action.payload;
   }
   ),
   switchMap(createGameRequest => this.gameService.createGame(createGameRequest)),
   map(gameId => new GameCreatedAction(gameId)));
}
