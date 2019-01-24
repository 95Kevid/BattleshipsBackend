import {Ship} from '../models/ship';
// import {AddShipSuceededAction, InitialiseGridAction} from './actions';
import {State} from './state';
import {Cell} from '../models/cell';
import {Row} from '../models/row';
import {Action} from '@ngrx/store';

import * as actions from './actions';

import { ShipActions } from './actions';


const initialState: State = {
  ships : [],
  tableRows : [],
  tableHeaders : []
}

export function reducers(state: State = initialState, action: ShipActions) {
  switch (action.type) {
    case actions.ADD_SHIP_SUCCESS: {
      const newState: State =  { ...state };
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

export function initialiseGrid(state: State = initialState, action: InitialiseGridAction) {
  switch (action.type) {
    case 'INITIALISE_GRID': {
      console.log("INITIALISE GRID ACTION RECEIVED");
      const tableHeaders: String[] = [];
      const tableRows: Row[] = [];
      tableHeaders[0] = 'Row';
      for (let i = 0; i < action.payload; i++) {
        tableHeaders[i + 1] = String.fromCharCode(65 + i);
      }

      for (let i = 0; i < action.payload; i++) {
        const cells: Cell[] = [];
        for (let j = 0; j < action.payload; j++) {
          const cell: Cell = {
            col: tableHeaders[j + 1].toString(),
            row: (i + 1).toString(),
            colour: 'blue',
            hit: false,
            equals: c => {
              return c.col === cell.col
                && c.row === cell.row;
            }
          };
          cells[j] = cell;
        }
        const row: Row = new Row(cells);
        tableRows[i] = row;
        console.log(tableRows);
      }
      const outputState =  {...state};
      outputState.tableHeaders = tableHeaders;
      outputState.tableRows = tableRows;
      return outputState;
    }
    default: {
      return state;
    }
  }
}
