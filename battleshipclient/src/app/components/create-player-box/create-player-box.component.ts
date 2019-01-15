import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-create-player-box',
  templateUrl: './create-player-box.component.html',
  styleUrls: ['./create-player-box.component.scss']
})
export class CreatePlayerBoxComponent implements OnInit {

  constructor() { }

  @Input() playerId: number;
  @Output() createPlayerRequest = new EventEmitter();
  createPlayerFormGroup: FormGroup = new FormGroup({
    playerName: new FormControl()
  });
  formIsDisabled = false;
  ngOnInit() {
  }

  onSubmit() {
    this.createPlayerRequest.emit(this.createPlayerFormGroup.controls['playerName'].value);
    this.disableForm();
  }

  disableForm() {
    this.createPlayerFormGroup.disable();
    this.formIsDisabled = true;
  }

}
