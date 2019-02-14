import {Action} from '@ngrx/store';
import {Ship} from '../../models/ship';

export class InitialiseGridAction implements Action {
  constructor(public payload: number) {
  }

  readonly type = 'INITIALISE_GRID';
}

export class RenderShipAction implements Action {
  constructor(public payload: Ship) {
  }

  readonly type = 'RENDER_SHIP';
}

export type GridActions = InitialiseGridAction | RenderShipAction;
