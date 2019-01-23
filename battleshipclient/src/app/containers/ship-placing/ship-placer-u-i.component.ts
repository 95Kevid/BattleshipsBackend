import { Component, OnInit } from '@angular/core';
import {ShipPlaceRequest} from '../../models/ship-place-request';
import {ShipPlacingService} from '../../services/ship-placing.service';
import {Observable} from 'rxjs';
import {Ship} from '../../models/ship';
import {PlayerService} from '../../services/player.service';
import {GameService} from '../../services/game.service';
import {Store} from '@ngrx/store';
import {State} from '../../store/state';
import {AddSubmarineEndPointAction} from '../../store/actions';

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

  constructor(playerService: PlayerService, gameService: GameService, store: Store<State>) {
    this.playerService = playerService;
    this.gameService = gameService;
    this.store = store;
  }

  ngOnInit() {

  }

  placeSubmarine(shipPlaceRequest: ShipPlaceRequest) {
    shipPlaceRequest.gameId = this.gameService.gameId;
    shipPlaceRequest.playerId = this.playerService.playerId;
    this.store.dispatch(new AddSubmarineEndPointAction(shipPlaceRequest));
  }

  placeDestroyer(shipPlaceRequest: ShipPlaceRequest) {

  }

  placeCarrier(shipPlaceRequest: ShipPlaceRequest) {

  }

  placeCruiser(shipPlaceRequest: ShipPlaceRequest) {

  }

  placeBattleship(shipPlaceRequest: ShipPlaceRequest) {

  }
}
