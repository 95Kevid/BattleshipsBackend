import {Component, EventEmitter, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-game-control-box',
  templateUrl: './game-control-box.component.html',
  styleUrls: ['./game-control-box.component.scss']
})
export class GameControlBoxComponent implements OnInit {

  constructor() { }
  @Output() callForPlayerPollEvent: EventEmitter<void> = new EventEmitter();

  ngOnInit() {
  }

  pollForPlayersToPlayersReady() {
    this.callForPlayerPollEvent.emit();
  }



}
