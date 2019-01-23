import {Action} from '@ngrx/store';
import {Ship} from '../models/ship';
import {ShipPlaceRequest} from '../models/ship-place-request';

export class CallAddSubmarineEndPoint implements Action {
  constructor(public payload: ShipPlaceRequest) {}
  readonly type = 'CALL_ADD_SUBMARINE';
}

export class CallAddBattleshipEndPoint implements Action {
  constructor(public payload: ShipPlaceRequest) {}
  readonly type = 'CALL_ADD_BATTLESHIP';
}

export class CallAddDestroyerEndPoint implements Action {
  constructor(public payload: ShipPlaceRequest) {}
  readonly type = 'CALL_ADD_DESTROYER';
}

export class CallAddCarrierEndPoint implements Action {
  constructor(public payload: ShipPlaceRequest) {}
  readonly type = 'CALL_ADD_CARRIER';
}

export class CallAddCruiserEndPoint implements Action {
  constructor(public payload: ShipPlaceRequest) {}
  readonly type = 'CALL_ADD_CRUISER';
}
