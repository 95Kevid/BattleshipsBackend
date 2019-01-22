import { Component, OnInit } from '@angular/core';
import {ShipPlaceRequest} from '../../models/ship-place-request';
import {ShipPlacingService} from '../../services/ship-placing.service';
import {Observable} from 'rxjs';
import {Ship} from '../../models/ship';
import {PlayerService} from '../../services/player.service';
import {GameService} from '../../services/game.service';

@Component({
  selector: 'app-ship-placer',
  templateUrl: './ship-placer-u-i.component.html',
  styleUrls: ['./ship-placer-u-i.component.scss']
})
export class ShipPlacerUIComponent implements OnInit {

  title: String = 'Ship Placing';
  shipPlacingService: ShipPlacingService;
  playerService: PlayerService;
  gameService: GameService;

  constructor(shipPlacingService: ShipPlacingService, playerService: PlayerService, gameService: GameService) {
    this.shipPlacingService = shipPlacingService;
    this.playerService = playerService;
    this.gameService = gameService;
  }

  ngOnInit() {

  }

  placeSubmarine(shipPlaceRequest: ShipPlaceRequest) {
    shipPlaceRequest.gameId = this.gameService.gameId;
    shipPlaceRequest.playerId = this.playerService.playerId;
    this.shipPlacingService.placeSubmarine(shipPlaceRequest);
  }

  placeDestroyer(shipPlaceRequest: ShipPlaceRequest) {
    this.shipPlacingService.placeDestoyer(shipPlaceRequest).subscribe((result) =>  console.log(result));
  }

  placeCarrier(shipPlaceRequest: ShipPlaceRequest) {
    this.shipPlacingService.placeCarrier(shipPlaceRequest).subscribe((result) =>  console.log(result));
  }

  placeCruiser(shipPlaceRequest: ShipPlaceRequest) {
    this.shipPlacingService.placeCruiser(shipPlaceRequest).subscribe((result) =>  console.log(result));
  }

  placeBattleship(shipPlaceRequest: ShipPlaceRequest) {
    this.shipPlacingService.placeBattleship(shipPlaceRequest).subscribe((result) =>  console.log(result));
  }
}
