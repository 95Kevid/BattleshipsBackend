import {Component, OnInit} from '@angular/core';
import {Row} from '../models/row';
import {ShipPlacingService} from '../services/ship-placing.service';
import {Observable} from 'rxjs';
import {Store} from '@ngrx/store';
import {InitialiseGridAction} from '../store/grid/grid.actions';
import {AppState} from '../store';


@Component({
  selector: 'app-grid',
  templateUrl: './grid.component.html',
  styleUrls: ['./grid.component.scss']
})

export class GridComponent implements OnInit {
  gridSize: number;
  tableHeaders$: Observable<string[]>;
  tableRows$: Observable<Row[]>;

  constructor(private shipPlacingService: ShipPlacingService, private store: Store<AppState>) {}

  ngOnInit() {
    this.tableHeaders$ = this.store.select(state => state.gridState.tableHeaders);
    this.tableRows$ = this.store.select(state => state.gridState.tableRows);
    this.tableHeaders$.subscribe(console.log);
  }
}
