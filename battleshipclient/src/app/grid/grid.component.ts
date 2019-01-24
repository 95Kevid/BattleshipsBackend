import {Component, OnInit} from '@angular/core';
import {Row} from '../models/row';
import {Cell} from '../models/cell';
import {ShipPlacingService} from '../services/ship-placing.service';
import {Observable} from 'rxjs';
import {Ship} from '../models/ship';
import {Store} from '@ngrx/store';
import {State} from '../store/state';
import {map, mapTo, tap} from 'rxjs/operators';

@Component({
  selector: 'app-grid',
  templateUrl: './grid.component.html',
  styleUrls: ['./grid.component.scss']
})
export class GridComponent implements OnInit {

  gridSize = 10;
  tableHeaders: String[] = [];
  tableRows: Row[] = [];
  ships$: Observable<Ship[]>;
  letters: string[] = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M'];
  numbers: string[] = ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13' ];

  constructor(private shipPlacingService: ShipPlacingService, private store: Store<State>) {
    this.ships$ = this.store.select(state => state.ships);
    this.ships$.pipe(
      tap(ships => {
        ships.forEach(ship => this.renderShip(ship));
      })
    );
  }

  ngOnInit() {
    this.loadGrid();
  }


  renderShip(ship: Ship) {
    for (const shipCell of ship.occupiedBoardPositions) {
      this.tableRows[this.numbers.indexOf(shipCell.row)].cells[this.letters.indexOf(shipCell.col)].colour = 'pink'; // Alex's way
    }
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
          col: this.tableHeaders[j + 1].toString(),
          row: (i + 1).toString(),
          colour: 'white',
          hit: false,
          equals: c => {
            return c.col === cell.col
              && c.row === cell.row;
          }
        };
        cells[j] = cell;
      }
      const row: Row = new Row(cells);
      this.tableRows[i] = row;
      console.log(this.tableRows);
    }
  }

  placeShip(): void {
    const cellA1: Cell = {
       col: 'A',
       colour: 'white',
       hit: false,
       row: '1',
       equals: cell => {
         return cell.col === cellA1.col
         && cell.row === cellA1.row;
       }
    };

    const cellB1: Cell = {
      col: 'B',
      colour: 'white',
      hit: false,
      row: '1',
      equals: cell => {
        return cell.col === cellB1.col
          && cell.row === cellB1.row;
      }
    };

    const cells: Cell[] = [cellA1, cellB1];

    const ship: Ship = {
      occupiedBoardPositions: cells,
      orient: 'HORIZONTAL',
      length: 2,
      sunk: false
    };

    this.renderShip(ship);
  }


}
