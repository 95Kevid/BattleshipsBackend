import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {ShipPlaceRequest} from '../../models/ship-place-request';
import {Observable} from 'rxjs';
import {GridState} from '../../store/grid/grid.reducers';

@Component({
  selector: 'app-ship-position-box',
  templateUrl: './ship-position-box.component.html',
  styleUrls: ['./ship-position-box.component.scss']
})
export class ShipPositionBoxComponent implements OnInit {

  @Input() shipType: string;
  @Input() tableHeaders$: Observable<string[]>;
  @Output() shipPlacementUpdate: EventEmitter<ShipPlaceRequest> = new EventEmitter();
  selectableHeaders: string[];


  shipPlacingForm = new FormGroup({
    col: new FormControl(''),
    row: new FormControl(''),
    orientation: new FormControl('')
  });

  constructor() {}

  private shipPlaceRequest: ShipPlaceRequest = new ShipPlaceRequest();

  ngOnInit() {
    //this.tableHeaders$.subscribe(headers => this.selectableHeaders = headers);
  }

  submitPlacement() {
    this.shipPlaceRequest.boardPosition.col = this.shipPlacingForm.get('col').value;
    this.shipPlaceRequest.boardPosition.row = this.shipPlacingForm.get('row').value;
    this.shipPlaceRequest.orientation = this.shipPlacingForm.get('orientation').value;
    this.shipPlacementUpdate.emit(this.shipPlaceRequest);
  }
}
