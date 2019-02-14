import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PollingService {
  constructor(private http: HttpClient) {
  }

  private url = 'http://localhost:9721/waitingplayerpoll';

  pollToStartGame(gameId: number): Observable<PlayersToPlayersReady> {
     return this.http.post<PlayersToPlayersReady>(this.url, gameId);
  }
}
