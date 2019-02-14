import { Component, OnInit } from '@angular/core';
import {Observable} from 'rxjs';
import {Row} from '../../../models/row';
import {ShipPlacingService} from '../../../services/ship-placing.service';
import {Store} from '@ngrx/store';
import {AppState} from '../../../store';

@Component({
  selector: 'app-game-grid',
  templateUrl: './game-grid.component.html',
  styleUrls: ['./game-grid.component.scss']
})
export class GameGridComponent implements OnInit {

  gridSize$: Observable<number>;
  tableHeaders$: Observable<string[]>;
  tableRows$: Observable<Row[]>;

  constructor(private shipPlacingService: ShipPlacingService, private store: Store<AppState>) {
  }

  ngOnInit() {
    this.tableHeaders$ = this.store.select(state => state.gridState.tableHeaders);
    this.tableRows$ = this.store.select(state => state.gridState.tableRows);
    this.gridSize$ = this.store.select(state => state.gameState.gameId);
  }
}
