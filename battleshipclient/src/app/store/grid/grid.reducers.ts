import {Row} from '../../models/row';
import {Cell} from '../../models/cell';
import {GridActions} from './grid.actions';


export interface GridState {
  tableRows: Row[];
  lengthOfRows: number;
  tableHeaders: string[];
  gridSize: number;
}

export const initialGridState: GridState = {
  tableRows : [],
  tableHeaders : [],
  lengthOfRows: 0,
  gridSize: 0
};

export function gridReducers(state: GridState = initialGridState, action: GridActions) {
  switch (action.type) {
    case 'INITIALISE_GRID': return initialiseGrid(state, action);
    case 'RENDER_SHIP': {
      const outputState = {...state};
      for (const shipCell of action.payload.occupiedBoardPositions) {
        outputState.tableRows[shipCell.row - 1].cells[shipCell.col.charCodeAt(0) - 65].colour = 'pink';
      }
      return outputState;
    }
    default: {
      return state;
    }
  }
}

function initialiseGrid(state: GridState = initialGridState, action: GridActions) {
    console.log('INITIALISE GRID ACTION RECEIVED');
    const tableHeaders: string[] = [];
    const tableRows: Row[] = [];
    for (let i = 0; i < action.payload; i++) {
      tableHeaders[i] = String.fromCharCode(65 + i);
    }

    for (let i = 0; i < action.payload; i++) {
      const cells: Cell[] = [];
      for (let j = 0; j < action.payload; j++) {
        const cell: Cell = {
          col: tableHeaders[j].toString(),
          row: (i),
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
    const outputState = {...state};
    outputState.tableHeaders = tableHeaders;
    outputState.tableRows = tableRows;
    outputState.lengthOfRows = tableRows.length;
    return outputState;
}




