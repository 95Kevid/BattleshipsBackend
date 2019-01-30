import {Action} from '@ngrx/store';
import {Ship} from '../../models/ship';
import {ShipPlaceRequest} from '../../models/ship-place-request';

export const ADD_SHIP_SUCCESS = 'ADD_SHIP_SUCCESS';

export class AddSubmarineRequestAction implements Action {
  constructor(public payload: ShipPlaceRequest) {}
  readonly type = 'CALL_ADD_SUBMARINE';
}

export class AddBattleshipRequestAction implements Action {
  constructor(public readonly payload: ShipPlaceRequest) {}
  readonly type = 'CALL_ADD_BATTLESHIP';
}

export class AddDestroyerRequestAction implements Action {
  constructor(public payload: ShipPlaceRequest) {}
  readonly type = 'CALL_ADD_DESTROYER';
}

export class AddCarrierRequestAction implements Action {
  constructor(public payload: ShipPlaceRequest) {}
  readonly type = 'CALL_ADD_CARRIER';
}

export class AddCruiserRequestAction implements Action {
  constructor(public payload: ShipPlaceRequest) {}
  readonly type = 'CALL_ADD_CRUISER';
}



export class AddShipSuceededAction implements Action {
  readonly type = ADD_SHIP_SUCCESS;
  constructor(public payload: Ship) {}
}

export type ShipActions = AddShipSuceededAction;
