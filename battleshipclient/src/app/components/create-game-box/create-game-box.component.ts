import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {CreateGameRequest} from '../../models/create-game-request';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-create-game-box',
  templateUrl: './create-game-box.component.html',
  styleUrls: ['./create-game-box.component.scss']
})
export class CreateGameBoxComponent implements OnInit {

  constructor() {
  }

  @Input() gameId$: Observable<number>;
  @Output() createGameEvent: EventEmitter<CreateGameRequest> = new EventEmitter();

  formGroup: FormGroup = new FormGroup({
      numberOfPlayers: new FormControl(''),
      gridSize: new FormControl('')
    }
  );

  formDisabled = false;

  ngOnInit() {
  }

  onSubmit() {
    console.log('submit called');
    const numberOfPlayers: number = this.formGroup.controls['numberOfPlayers'].value;
    const gridSize: number = this.formGroup.controls['gridSize'].value;
    const createGameRequest: CreateGameRequest = {
      numberOfPlayers: numberOfPlayers,
      gridSize: gridSize
    };

    this.createGameEvent.emit(createGameRequest);
    this.disableForm();
  }

  private disableForm() {
    this.formGroup.disable();
    this.formDisabled = true;
  }
}
