import {Component, OnInit} from '@angular/core';
import {Row} from '../models/row';
import {Cell} from '../models/cell';
import {ShipPlacingService} from '../services/ship-placing.service';
import {Observable} from 'rxjs';
import {Ship} from '../models/ship';
import {ShipPlaceRequest} from '../models/ship-place-request';

@Component({
  selector: 'app-grid',
  templateUrl: './grid.component.html',
  styleUrls: ['./grid.component.scss']
})
export class GridComponent implements OnInit {

  gridSize: number = 10;
  tableHeaders: String[] = [];
  tableRows: Row[] = [];
  ships$: Observable<Ship[]>;

  constructor(private shipPlacingService: ShipPlacingService) {}

  ngOnInit() {
    this.loadGrid();
    this.poppy();
    this.ships$ = this.shipPlacingService.getShips();
  }

  loadGrid(): void {
    this.tableHeaders[0] = 'Row';
    for (let i = 0; i < this.gridSize; i++) {
      this.tableHeaders[i + 1] = String.fromCharCode(65 + i);
    }

    for (let i = 0; i < this.gridSize; i++) {
      const cells: Cell[] = [];
      for (let j = 0; j < this.gridSize; j++) {
        const cell: Cell = {
          col: this.tableHeaders[j + 1] + (i + 1).toString(),
          row: (i + 1).toString(),
          colour: 'white',
          hit: false
        };
        cells[j] = cell;
      }
      const row: Row = new Row(cells);
      this.tableRows[i] = row;
      console.log(this.tableRows);
    }
  }

  poppy(): void {
    this.tableRows[3].cells[1].colour = 'red';
  }


}
