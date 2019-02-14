import {Ship} from '../../models/ship';
import * as actions from './ship.actions';
import {ShipActions} from './ship.actions';

export interface ShipState {
  ships: Ship[];
}

export const initialShipState: ShipState = {
  ships: []
};


export function shipReducers(state: ShipState = initialShipState, action: ShipActions) {
  switch (action.type) {
    case actions.ADD_SHIP_SUCCESS: {
      const newState: ShipState = {...state};
      const ships: Ship[] = initialShipState.ships;
      console.log('Ship reducer called');
      ships.push(action.payload);
      newState.ships = ships;
      return newState;
    }
    default: {
      return state;
    }
  }
}



