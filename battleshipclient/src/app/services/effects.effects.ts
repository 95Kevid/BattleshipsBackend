import { Injectable } from '@angular/core';
import {Actions, Effect, ofType} from '@ngrx/effects';
import {Observable} from 'rxjs';
import {Action} from '@ngrx/store';
import {flatMap, map, switchMap} from 'rxjs/operators';
import {ShipPlacingService} from './ship-placing.service';
import {CallAddSubmarineEndPoint} from '../store/actions';


@Injectable()
export class EffectsEffects {

  constructor(private actions$: Actions, private service: ShipPlacingService) {}

  @Effect()
  addBattleShip$: Observable<Action> = this.actions$.pipe(
    ofType<CallAddSubmarineEndPoint>('CALL_ADD_BATTLESHIP'),
    flatMap((action) => {
      const ship = action.payload;
      return this.service.placeBattleship(ship);
    })

  )
}
