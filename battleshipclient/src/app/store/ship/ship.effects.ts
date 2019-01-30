import {Injectable} from '@angular/core';
import {Actions, Effect, ofType} from '@ngrx/effects';
import {map, switchMap} from 'rxjs/operators';
import {ShipPlacingService} from '../../services/ship-placing.service';
import {
  AddBattleshipRequestAction,
  AddCarrierRequestAction, AddDestroyerRequestAction,
  AddShipSuceededAction,
  AddSubmarineRequestAction,
  ShipActions
} from './ship.actions';
import {RenderShipAction} from '../grid/grid.actions';
@Injectable()
export class ShipEffects {

  constructor(private actions$: Actions, private shipPlacingService: ShipPlacingService) {
  }

  @Effect()
  public addBattleShip$ = this.actions$.pipe(
    ofType<AddBattleshipRequestAction>('CALL_ADD_BATTLESHIP'),
    map(action => action.payload),
    switchMap(payload => this.shipPlacingService.placeBattleship(payload)),
    switchMap(ship => [new AddShipSuceededAction(ship),
        new RenderShipAction(ship)])
    );

  @Effect()
  public addSubmarine$ = this.actions$.pipe(
    ofType<AddSubmarineRequestAction>('CALL_ADD_SUBMARINE'),
    map(action => action.payload),
    switchMap(payload => this.shipPlacingService.placeSubmarine(payload)),
    switchMap(ship => [new AddShipSuceededAction(ship),
      new RenderShipAction(ship)])
  );

  @Effect()
  public addCruiser$ = this.actions$.pipe(
    ofType<AddCarrierRequestAction>('CALL_ADD_CRUISER'),
    map(action => action.payload),
    switchMap(payload => this.shipPlacingService.placeCruiser(payload)),
    switchMap(ship => [new AddShipSuceededAction(ship),
      new RenderShipAction(ship)])
  );

  @Effect()
  public addCarrier$ = this.actions$.pipe(
    ofType<AddCarrierRequestAction>('CALL_ADD_CARRIER'),
    map(action => action.payload),
    switchMap(payload => this.shipPlacingService.placeCarrier(payload)),
    switchMap(ship => [new AddShipSuceededAction(ship),
      new RenderShipAction(ship)])
  );

  @Effect()
  public addDestroyer$ = this.actions$.pipe(
    ofType<AddDestroyerRequestAction>('CALL_ADD_DESTROYER'),
    map(action => action.payload),
    switchMap(payload => this.shipPlacingService.placeDestroyer(payload)),
    switchMap(ship => [new AddShipSuceededAction(ship),
      new RenderShipAction(ship)])
  );
}
