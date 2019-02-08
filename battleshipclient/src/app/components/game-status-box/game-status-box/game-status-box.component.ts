import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-game-status-box',
  templateUrl: './game-status-box.component.html',
  styleUrls: ['./game-status-box.component.scss']
})
export class GameStatusBoxComponent {
  @Input playersReady: number;
  @Input numberOfPlayers: number;

}
