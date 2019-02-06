import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {ShipPlaceRequest} from '../../models/ship-place-request';
import {Observable} from 'rxjs';
import {GridState} from '../../store/grid/grid.reducers';
import {Row} from '../../models/row';

@Component({
  selector: 'app-ship-position-box',
  templateUrl: './ship-position-box.component.html',
  styleUrls: ['./ship-position-box.component.scss']
})
export class ShipPositionBoxComponent implements OnInit {

  constructor() {

  }

  @Input() shipType: string;
  @Input() tableHeaders$: Observable<string[]>;
  @Input() lengthOfRows$: Observable<number>;
  @Output() shipPlacementUpdate: EventEmitter<ShipPlaceRequest> = new EventEmitter();
  rowNumbers: number[];

  shipPlacingForm = new FormGroup({
    col: new FormControl(''),
    row: new FormControl(''),
    orientation: new FormControl('')
  });

  private shipPlaceRequest: ShipPlaceRequest = new ShipPlaceRequest();

  ngOnInit() {
    this.lengthOfRows$.subscribe(length => this.calculateAvailableRows(length));
  }

  submitPlacement() {
    this.shipPlaceRequest.boardPosition.col = this.shipPlacingForm.get('col').value;
    this.shipPlaceRequest.boardPosition.row = this.shipPlacingForm.get('row').value;
    this.shipPlaceRequest.orientation = this.shipPlacingForm.get('orientation').value;
    this.shipPlacementUpdate.emit(this.shipPlaceRequest);
  }

  calculateAvailableRows(length: number) {
    const array: number[] = new Array(length);
    for (let i = 0; i < length; i++) {
      array[i] = i + 1;
    }
    this.rowNumbers =  array;
  }
}
