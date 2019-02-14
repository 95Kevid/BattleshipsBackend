import {Row} from '../../models/row';
import {Cell} from '../../models/cell';
import {GridActions} from './grid.actions';
import {GridParameters} from '../../models/gridParameters';


export interface GridState {
  tableRows: Row[];
  lengthOfRows: number;
  tableHeaders: string[];
  gridSize: number;
}

export const initialGridState: GridState = {
  tableRows: [],
  tableHeaders: [],
  lengthOfRows: 0,
  gridSize: 0
};

function renderShip(state: GridState, occupiedBoardPositions: Cell[]) {
  const outputState = {...state};
  for (const shipCell of occupiedBoardPositions) {
    outputState.tableRows[shipCell.row - 1].cells[shipCell.col.charCodeAt(0) - 65].colour = 'pink';
  }
  return outputState;
}

export function gridReducers(state: GridState = initialGridState, action: GridActions) {
  switch (action.type) {
    case 'INITIALISE_GRID': {
      const gridParameters = initialiseGrid(action.payload);
      return {
        ...state,
        tableHeaders: gridParameters.tableHeaders,
        tableRows: gridParameters.tableRows,
        lengthOfRows: gridParameters.tableRows.length,
      };
    }
    case 'RENDER_SHIP': {
      return renderShip(state, action.payload.occupiedBoardPositions);
    }
    default: {
      return state;
    }
  }
}

function initialiseGrid(gridSize: number): GridParameters {
  const gridParameters: GridParameters = new GridParameters();
  gridParameters.tableHeaders = [];
  gridParameters.tableRows = [];

  for (let i = 0; i < gridSize; i++) {
    gridParameters.tableHeaders[i] = String.fromCharCode(65 + i);
  }

  for (let i = 0; i < gridSize; i++) {
    const cells: Cell[] = [];
    for (let j = 0; j < gridSize; j++) {
      const cell: Cell = {
        col: gridParameters.tableHeaders[j].toString(),
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
    gridParameters.tableRows[i] = new Row(cells);
  }
  return gridParameters;
}





