import {Ship} from '../models/ship';
import {AddShipSuceededAction} from './actions';
import {State} from './state';
import {Action} from '@ngrx/store';

const initialState: State = {
  ships : []
}

export function reducer(state: State =  initialState, action: AddShipSuceededAction) {
  switch (action.type) {
    case 'ADD_SHIP_SUCCESS': {
      const newState: State =  {...state};
      const ships: Ship[] = initialState.ships;
      ships.push(action.payload);
      newState.ships = ships;
      return newState;
    }
    default: {
      return state;
    }
  }
}
