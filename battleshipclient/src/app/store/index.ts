import {gridReducers, GridState} from './grid/grid.reducers';
import {shipReducers, ShipState} from './ship/ship.reducers';
import {ActionReducerMap} from '@ngrx/store';
import {gameReducers, GameState} from './game/game.reducers';

export interface AppState {
  shipState: ShipState;
  gridState: GridState;
  gameState: GameState;
}

export const reducers: ActionReducerMap<AppState> = {
  shipState: shipReducers,
  gridState: gridReducers,
  gameState: gameReducers
};
