import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {CreateGameRequest} from '../models/create-game-request';
import {Observable} from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class GameService {

  private url = 'http://localhost:9721/creategame/';
  gameId: number;

  constructor(private http: HttpClient) {
  }

  createGame(createGameRequest: CreateGameRequest): Observable<number> {
    const request = createGameRequest;
    return this.http.post<number>(this.url + request.numberOfPlayers + '/' + request.gridSize, null);
  }
}
