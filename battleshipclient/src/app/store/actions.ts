import {Action} from '@ngrx/store';
import {Ship} from '../models/ship';

export class AddShip implements Action {
  constructor(public payload: Ship) {}
  readonly type = 'ADD_SHIP';
}
