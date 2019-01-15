import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {ShipPlaceRequest} from '../../models/ship-place-request';

@Component({
  selector: 'app-ship-position-box',
  templateUrl: './ship-position-box.component.html',
  styleUrls: ['./ship-position-box.component.scss']
})
export class ShipPositionBoxComponent implements OnInit {

  @Input() shipType: string;
  @Output() shipPlacementUpdate: EventEmitter<ShipPlaceRequest> = new EventEmitter();


  shipPlacingForm = new FormGroup({
    col: new FormControl(''),
    row: new FormControl(''),
    orientation: new FormControl('')
  });

  constructor() {
  }

  private shipPlaceRequest: ShipPlaceRequest = new ShipPlaceRequest();

  ngOnInit() {
  }

  submitPlacement() {
    this.shipPlaceRequest.boardPosition.col = this.shipPlacingForm.get('col').value;
    this.shipPlaceRequest.boardPosition.row = this.shipPlacingForm.get('row').value;
    this.shipPlaceRequest.orientation = this.shipPlacingForm.get('orientation').value;
    this.shipPlacementUpdate.emit(this.shipPlaceRequest);
  }
}
