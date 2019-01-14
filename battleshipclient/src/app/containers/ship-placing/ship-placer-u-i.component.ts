import { Component, OnInit } from '@angular/core';
import {ShipPlaceRequest} from '../../models/ship-place-request';
import {ShipPlacingService} from '../../services/ship-placing.service';

@Component({
  selector: 'app-ship-placer',
  templateUrl: './ship-placer-u-i.component.html',
  styleUrls: ['./ship-placer-u-i.component.scss']
})
export class ShipPlacerUIComponent implements OnInit {

  title: String = 'Ship Placing';
  shipPlacingService: ShipPlacingService;

  constructor(shipPlacingService: ShipPlacingService) {
    this.shipPlacingService = shipPlacingService;
  }

  ngOnInit() {

  }

  placeSubmarine(shipPlaceRequest: ShipPlaceRequest) {
    this.shipPlacingService.placeSubmarine(shipPlaceRequest).subscribe((result) =>  console.log(result));
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
