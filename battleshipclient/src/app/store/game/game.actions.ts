import {Action} from '@ngrx/store';
import {CreateGameRequest} from '../../models/create-game-request';

export class PlayersToPlayersReadyPollAction implements Action {
  constructor(public payload: number) {}
  readonly type = 'PLAYERS_TO_PLAYERS_READY_REQUEST';
}

export class PlayersToPlayersReadyPollSuccessAction implements Action {
  constructor(public payload: PlayersToPlayersReady) {}
  readonly type = 'PLAYERS_TO_PLAYERS_READY_SUCCESS';
}

export class CreateGameRequestAction implements Action {
  constructor(public payload: CreateGameRequest) {}
  readonly type = 'CREATE_GAME';
}

export class GameCreatedAction implements Action {
  constructor(public payload: number) {}
  readonly type = 'GAME_CREATED';
}

export type GameActions = CreateGameRequestAction | GameCreatedAction | PlayersToPlayersReadyPollAction
  | PlayersToPlayersReadyPollSuccessAction;
