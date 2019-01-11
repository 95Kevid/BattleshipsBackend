import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {ShipPlaceRequest} from '../models/ship-place-request';
import {Observable, of} from 'rxjs';
import {Ship} from '../models/ship';

@Injectable({
  providedIn: 'root'
})
export class ShipPlacingService {
  private url = 'http://localhost:9721/';

  private ships: Ship[] = [];

  constructor(private http: HttpClient) {
  }

  placeBattleship(shipPlaceRequest: ShipPlaceRequest): Observable<Ship> {
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
    return of(this.ships);
  }
}
