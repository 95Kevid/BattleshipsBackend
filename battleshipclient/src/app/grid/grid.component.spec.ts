import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GridComponent } from './grid.component';
import {ShipPlacerUIComponent} from '../containers/ship-placing/ship-placer-u-i.component';
import {Cell} from '../models/cell';
import {Row} from '../models/row';

describe('GridComponent', () => {
  let component: GridComponent;
  let fixture: ComponentFixture<GridComponent>;


  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GridComponent,
        ShipPlacerUIComponent
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GridComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    component.loadGrid();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('Number of rows should be equal to the grid size', () => {
    expect(component.tableRows.length).toBe(component.gridSize);
  });

  it('Number of table headers should be equal to the grid size plus 1', () => {
    expect(component.tableHeaders.length).toBe(component.gridSize + 1);
  });

  it('the number of cells in each row should be equal to the grid size', () => {
    expect(component.tableRows.filter(r => r.cells.length === component.gridSize).length === component.gridSize);
  });
});
