import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CreateGameService {

  private url = 'http://localhost:9721/creategame/'
  constructor(private http: HttpClient) {}

  createGame(numberOfPlayers: number): Observable<number> {
    return this.http.post<number>(this.url + numberOfPlayers, null);
  }

}
