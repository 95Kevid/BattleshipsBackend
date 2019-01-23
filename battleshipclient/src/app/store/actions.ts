import {Action} from '@ngrx/store';
import {Ship} from '../models/ship';
import {ShipPlaceRequest} from '../models/ship-place-request';

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
  constructor(public payload: Ship) {}
  readonly type = 'ADD_SHIP_SUCCESS';
}




