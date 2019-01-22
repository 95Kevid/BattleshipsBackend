import {Ship} from '../models/ship';
import {AddShip} from './actions';

export function reducer(ships: Ship[], action: AddShip ) {
  switch (action.type) {
    case 'ADD_SHIP': {
      const newState: Ship[] = [...ships];
      newState.push(action.payload);
      return newState;
    }
    default: {
      return ships;
    }
  }
}
