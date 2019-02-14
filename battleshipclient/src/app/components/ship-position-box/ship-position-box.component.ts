import {Component, EventEmitter, Input, Output} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {ShipPlaceRequest} from '../../models/ship-place-request';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-ship-position-box',
  templateUrl: './ship-position-box.component.html',
  styleUrls: ['./ship-position-box.component.scss']
})
export class ShipPositionBoxComponent {

  constructor() {

  }

  @Input() shipType: string;
  @Input() tableHeaders$: Observable<string[]>;
  @Input() rowNumbers: number[];
  @Output() shipPlacementUpdate: EventEmitter<ShipPlaceRequest> = new EventEmitter();

  shipPlacingForm = new FormGroup({
    col: new FormControl(''),
    row: new FormControl(''),
    orientation: new FormControl('')
  });

  buttonDisabled = false;

  private shipPlaceRequest: ShipPlaceRequest = new ShipPlaceRequest();

  submitPlacement() {
    this.shipPlaceRequest.boardPosition.col = this.shipPlacingForm.get('col').value;
    this.shipPlaceRequest.boardPosition.row = this.shipPlacingForm.get('row').value;
    this.shipPlaceRequest.orientation = this.shipPlacingForm.get('orientation').value;
    this.shipPlacementUpdate.emit(this.shipPlaceRequest);
    this.disableShipPlacementForm();
  }

  disableShipPlacementForm() {
    this.shipPlacingForm.disable();
    this.buttonDisabled = true;
  }
}
