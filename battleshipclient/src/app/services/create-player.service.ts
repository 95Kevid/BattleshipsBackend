import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class CreatePlayerService {
  constructor(private http: HttpClient) { }
  private url = 'http://localhost:9721/addplayer';

  createPlayer(gameId: number, playerName: string) {
      return this.http.post<number>(this.url, {gameId: gameId, playerName: playerName});
  }
}
