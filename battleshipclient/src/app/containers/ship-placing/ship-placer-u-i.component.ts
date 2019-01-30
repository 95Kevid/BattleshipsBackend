import {Component, OnInit} from '@angular/core';
import {ShipPlaceRequest} from '../../models/ship-place-request';
import {PlayerService} from '../../services/player.service';
import {GameService} from '../../services/game.service';
import {Store} from '@ngrx/store';
import {State} from '../../store/state';
import {
  AddBattleshipRequestAction,
  AddCarrierRequestAction,
  AddCruiserRequestAction,
  AddDestroyerRequestAction,
  AddSubmarineRequestAction
} from '../../store/ship/ship.actions';
import {Observable} from 'rxjs';
import {GridState} from '../../store/grid/grid.reducers';

@Component({
  selector: 'app-ship-placer',
  templateUrl: './ship-placer-u-i.component.html',
  styleUrls: ['./ship-placer-u-i.component.scss']
})
export class ShipPlacerUIComponent implements OnInit {

  title: String = 'Ship Placing';
  playerService: PlayerService;
  gameService: GameService;
  store: Store<State>;
  tableHeaders$: Observable<string[]>;

  constructor(playerService: PlayerService, gameService: GameService, store: Store<State>) {
    this.playerService = playerService;
    this.gameService = gameService;
    this.store = store;
    this.tableHeaders$ = this.store.select(state => state.gridState.tableHeaders);
  }

  ngOnInit() {

  }

  placeSubmarine(shipPlaceRequest: ShipPlaceRequest) {
    shipPlaceRequest.gameId = this.gameService.gameId;
    shipPlaceRequest.playerId = this.playerService.playerId;
    this.store.dispatch(new AddSubmarineRequestAction(shipPlaceRequest));
  }

  placeDestroyer(shipPlaceRequest: ShipPlaceRequest) {
    shipPlaceRequest.gameId = this.gameService.gameId;
    shipPlaceRequest.playerId = this.playerService.playerId;
    this.store.dispatch(new AddDestroyerRequestAction(shipPlaceRequest));
  }

  placeCarrier(shipPlaceRequest: ShipPlaceRequest) {
    shipPlaceRequest.gameId = this.gameService.gameId;
    shipPlaceRequest.playerId = this.playerService.playerId;
    this.store.dispatch(new AddCarrierRequestAction(shipPlaceRequest));
  }

  placeCruiser(shipPlaceRequest: ShipPlaceRequest) {
    shipPlaceRequest.gameId = this.gameService.gameId;
    shipPlaceRequest.playerId = this.playerService.playerId;
    this.store.dispatch(new AddCruiserRequestAction(shipPlaceRequest));
  }

  placeBattleship(shipPlaceRequest: ShipPlaceRequest) {
    shipPlaceRequest.gameId = this.gameService.gameId;
    shipPlaceRequest.playerId = this.playerService.playerId;
    console.log('placeBattleship method in ship-placer component called');
    this.store.dispatch(new AddBattleshipRequestAction(shipPlaceRequest));
  }
}
