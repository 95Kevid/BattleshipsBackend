import {ShipState} from './ship/ship.reducers';
import {GridState} from './grid/grid.reducers';


export interface State {
  shipState: ShipState;
  gridState: GridState;
}
