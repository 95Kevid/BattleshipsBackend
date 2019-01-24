import {Ship} from '../models/ship';
import {Row} from '../models/row';

export interface State {
  ships: Ship[];
  tableRows: Row[];
  tableHeaders: String[]
}
