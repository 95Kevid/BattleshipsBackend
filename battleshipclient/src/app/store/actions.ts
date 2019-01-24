import {Action} from '@ngrx/store';
import {Ship} from '../models/ship';
import {ShipPlaceRequest} from '../models/ship-place-request';

export const ADD_SHIP_SUCCESS = 'ADD_SHIP_SUCCESS';

export class AddSubmarineEndPointAction implements Action {
  constructor(public payload: ShipPlaceRequest) {}
  readonly type = 'CALL_ADD_SUBMARINE';
}

export class AddBattleshipEndPointAction implements Action {
  constructor(public readonly payload: ShipPlaceRequest) {}
  readonly type = 'CALL_ADD_BATTLESHIP';
}

export class AddDestroyerEndPointAction implements Action {
  constructor(public payload: ShipPlaceRequest) {}
  readonly type = 'CALL_ADD_DESTROYER';
}

export class AddCarrierEndPointAction implements Action {
  constructor(public payload: ShipPlaceRequest) {}
  readonly type = 'CALL_ADD_CARRIER';
}

export class AddCruiserEndPointAction implements Action {
  constructor(public payload: ShipPlaceRequest) {}
  readonly type = 'CALL_ADD_CRUISER';
}



export class AddShipSuceededAction implements Action {
  readonly type = ADD_SHIP_SUCCESS;
  constructor(public payload: Ship) {}
}

export class InitialiseGridAction implements Action {
  constructor(public payload: Number) {}
  readonly type = 'INITIALISE_GRID';
}

export type ShipActions = AddShipSuceededAction;
