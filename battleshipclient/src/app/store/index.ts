import {gridReducers, GridState} from './grid/grid.reducers';
import {shipReducers, ShipState} from './ship/ship.reducers';
import {ActionReducerMap} from '@ngrx/store';

export interface AppState {
  shipState: ShipState;
  gridState: GridState;
}

export const reducers: ActionReducerMap<AppState> = {
  shipState: shipReducers,
  gridState: gridReducers
}
