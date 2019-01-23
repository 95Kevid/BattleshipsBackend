import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {ShipPlaceRequest} from '../models/ship-place-request';
import {Ship} from '../models/ship';
import {Store} from '@ngrx/store';
import {State} from '../store/state';
import {Actions, Effect, ofType} from '@ngrx/effects';
import {Observable} from 'rxjs';
import {Action} from '@ngrx/store';

@Injectable({
  providedIn: 'root'
})
export class ShipPlacingService {
  private url = 'http://localhost:9721/';



  constructor(private http: HttpClient, private store: Store<State>) {
  }


  placeBattleship(shipPlaceRequest: ShipPlaceRequest) {
    return this.http.post<Ship>(this.url + 'placebattleship', shipPlaceRequest);
  }

  placeSubmarine(shipPlaceRequest: ShipPlaceRequest) {
    return this.http.post<Ship>(this.url + 'placesubmarine', shipPlaceRequest);
  }

  placeCarrier(shipPlaceRequest: ShipPlaceRequest) {
    return this.http.post<Ship>(this.url + 'placecarrier', shipPlaceRequest);
  }

  placeDestoyer(shipPlaceRequest: ShipPlaceRequest) {
    return this.http.post<Ship>(this.url + 'placecruiser', shipPlaceRequest);
  }

  placeCruiser(shipPlaceRequest: ShipPlaceRequest) {
    return this.http.post<Ship>(this.url + 'placedestroyer', shipPlaceRequest);
  }

  getShips() {
    //return this.ships.asObservable();
  }

  // addShip(ship: Ship) {
  //
  // }
}
