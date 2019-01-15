import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-create-game-box',
  templateUrl: './create-game-box.component.html',
  styleUrls: ['./create-game-box.component.scss']
})
export class CreateGameBoxComponent implements OnInit {

  constructor() { }

  @Input() gameId: number;
  @Output() createGameEvent: EventEmitter<number> = new EventEmitter();


  formGroup: FormGroup = new FormGroup({
      numberOfPlayers: new FormControl('')
    }
  );

  formDisabled = false;

  ngOnInit() {
  }

  onSubmit() {
    this.createGameEvent.emit(this.formGroup.controls['numberOfPlayers'].value);
    this.disableForm();
  }

  private disableForm() {
    this.formGroup.disable();
    this.formDisabled = true;
  }

}
