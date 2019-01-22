import {Subject} from 'rxjs';

class Dispatcher extends Subject{
  dispatch(value : any) : void {
    this.next(value);
  }
}
