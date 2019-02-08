import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, timer} from 'rxjs';
import {concatMap, map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class PollingService {
  constructor(private http: HttpClient) { }
  private url = 'http://localhost:9721/waitingPlayerPoll';

  pollToStartGame(): Observable<PlayersToPlayersReady> {
    return timer(0, 1000).pipe(
      concatMap(_ => this.http.get(this.url)),
      map((response: PlayersToPlayersReady) => response));
  }
}
