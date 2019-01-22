import {BehaviorSubject} from 'rxjs';
import {Ship} from '../models/ship';

class Store extends BehaviorSubject<Ship[]> {
  constructor(initial)
}
