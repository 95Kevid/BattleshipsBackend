import {Injectable} from '@angular/core';
import {Actions, Effect, ofType} from '@ngrx/effects';
import {catchError, map, mergeMap, switchMap} from 'rxjs/operators';
import {ShipPlacingService} from '../../services/ship-placing.service';
import {
  AddBattleshipRequestAction,
  AddCarrierRequestAction, AddCruiserRequestAction, AddDestroyerRequestAction,
  AddShipSuceededAction,
  AddSubmarineRequestAction,
  ShipPlaceFailedAction
} from './ship.actions';
import {RenderShipAction} from '../grid/grid.actions';
import {of} from 'rxjs';
@Injectable()
export class ShipEffects {

  constructor(private actions$: Actions, private shipPlacingService: ShipPlacingService) {
  }

  @Effect()
  public addBattleship$ = this.actions$.pipe(
    ofType<AddBattleshipRequestAction>('CALL_ADD_BATTLESHIP'),
    map(action => action.payload),
    switchMap(payload => this.shipPlacingService.placeDestroyer(payload).pipe(
      mergeMap(ship => [new AddShipSuceededAction(ship),
        new RenderShipAction(ship)]),
      catchError((error) => {
        return of(new ShipPlaceFailedAction(error));
      }))
    ));

  @Effect()
  public addDestroyer$ = this.actions$.pipe(
    ofType<AddDestroyerRequestAction>('CALL_ADD_DESTROYER'),
    map(action => action.payload),
    switchMap(payload => this.shipPlacingService.placeDestroyer(payload).pipe(
      mergeMap(ship => [new AddShipSuceededAction(ship),
        new RenderShipAction(ship)]),
      catchError((error) => {
        return of(new ShipPlaceFailedAction(error));
      }))
    ));

  @Effect()
  public addCarrier$ = this.actions$.pipe(
    ofType<AddCarrierRequestAction>('CALL_ADD_CARRIER'),
    map(action => action.payload),
    switchMap(payload => this.shipPlacingService.placeCarrier(payload).pipe(
      mergeMap(ship => [new AddShipSuceededAction(ship),
        new RenderShipAction(ship)]),
      catchError((error) => {
        return of(new ShipPlaceFailedAction(error));
      }))
    ));

  @Effect()
  public addCruiser$ = this.actions$.pipe(
    ofType<AddCruiserRequestAction>('CALL_ADD_CRUISER'),
    map(action => action.payload),
    switchMap(payload => this.shipPlacingService.placeCruiser(payload).pipe(
      mergeMap(ship => [new AddShipSuceededAction(ship),
        new RenderShipAction(ship)]),
      catchError((error) => {
        return of(new ShipPlaceFailedAction(error));
      }))
    ));

  @Effect()
  public addSubmarine$ = this.actions$.pipe(
    ofType<AddSubmarineRequestAction>('CALL_ADD_SUBMARINE'),
    map(action => action.payload),
    switchMap(payload => this.shipPlacingService.placeSubmarine(payload).pipe(
      map(ship => of(new AddShipSuceededAction(ship),
        new RenderShipAction(ship))),
      catchError((error) => {
        return of(new ShipPlaceFailedAction(error));
      }))
    ));
}
