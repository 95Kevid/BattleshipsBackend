import {Injectable} from '@angular/core';
import {Actions, Effect, ofType} from '@ngrx/effects';
import {map, switchMap, tap} from 'rxjs/operators';
import {ShipPlacingService} from './ship-placing.service';
import {AddBattleshipEndPointAction, AddShipSuceededAction} from '../store/actions';


@Injectable()
export class EffectsEffects {

  constructor(private actions$: Actions, private shipPlacingService: ShipPlacingService) {
  }


  @Effect()
  addBattleShip$ = this.actions$.pipe(
    ofType<AddBattleshipEndPointAction>('CALL_ADD_BATTLESHIP'),
    map(action => action.payload), switchMap(payload =>
      this.shipPlacingService.placeBattleship(payload).pipe(map(ship => new AddShipSuceededAction(ship)))
    )
  );
}
