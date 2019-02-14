import {Component, Input, OnInit} from '@angular/core';
import {Row} from '../../models/row';
import {ShipPlacingService} from '../../services/ship-placing.service';
import {Observable} from 'rxjs';
import {Store} from '@ngrx/store';
import {AppState} from '../../store/index';

@Component({
  selector: 'app-grid-box',
  templateUrl: './grid.box.component.html',
  styleUrls: ['./grid-box.component.scss']
})

export class GridBoxComponent {
  @Input() gridSize: number;
  @Input() tableHeaders$: Observable<string[]>;
  @Input() tableRows$: Observable<Row[]>;

  constructor(private shipPlacingService: ShipPlacingService, private store: Store<AppState>) {
  }
}
