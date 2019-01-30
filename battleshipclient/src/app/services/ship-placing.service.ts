import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ShipPlaceRequest} from '../models/ship-place-request';
import {Ship} from '../models/ship';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ShipPlacingService {
  private url = 'http://localhost:9721/';

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

  placeDestroyer(shipPlaceRequest: ShipPlaceRequest) {
    return this.http.post<Ship>(this.url + 'placedestroyer', shipPlaceRequest);
  }

  placeCruiser(shipPlaceRequest: ShipPlaceRequest) {
    return this.http.post<Ship>(this.url + 'placecruiser', shipPlaceRequest);
  }
}
