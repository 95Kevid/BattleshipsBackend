import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class GameService {

  private url = 'http://localhost:9721/creategame/';
  gameId: number;
  constructor(private http: HttpClient) {}

  createGame(numberOfPlayers: number): number {
    this.http.post<number>(this.url + numberOfPlayers, null).subscribe(result => this.gameId = result);
    return this.gameId;
  }

}
