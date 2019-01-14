import {TestBed, async, ComponentFixture} from '@angular/core/testing';
import { AppComponent } from './app.component';
import {ShipPlacerUIComponent} from './containers/ship-placing/ship-placer-u-i.component';
import {GridComponent} from './grid/grid.component';

describe('AppComponent', () => {

  let fixture: ComponentFixture<AppComponent>;
  let component: AppComponent;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [
        AppComponent,
        ShipPlacerUIComponent,
        GridComponent
      ],
    });

    fixture = TestBed.createComponent(AppComponent);
    component = fixture.componentInstance;
  });

  it('should create the app', () => {
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  });






  // it('should create a grid with width of board size of 10', () => {
  //   component.boardSize = 10;
  //
  //   fixture.detectChanges();
  //   const rows = fixture.debugElement.queryAll(By.css('.row')).length;
  //   expect(rows).toBe(10);
  //
  //   expect(component.boardSize).toBe(10);
  // });
});
