import {Component, EventEmitter, Input, Output} from '@angular/core';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-game-status-box',
  templateUrl: './game-status-box.component.html',
  styleUrls: ['./game-status-box.component.scss']
})
export class GameStatusBoxComponent {
  @Input() playersReady$: Observable<number>;
  @Input() playersInGame$: Observable<number>;
}
