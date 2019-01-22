import {Ship} from '../models/ship';
import {AddShip} from './actions';
import {State} from './state';

const initialState: State = {
  ships : []
}

export function reducer(state: State =  initialState, action: AddShip ) {
  switch (action.type) {
    case 'ADD_SHIP': {
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
