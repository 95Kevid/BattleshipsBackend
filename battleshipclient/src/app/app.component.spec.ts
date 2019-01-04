import {TestBed, async, ComponentFixture} from '@angular/core/testing';
import { AppComponent } from './app.component';
import {By} from '@angular/platform-browser';

describe('AppComponent', () => {

  let fixture: ComponentFixture<AppComponent>;
  let component: AppComponent;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [
        AppComponent
      ],
    });

    fixture = TestBed.createComponent(AppComponent);
    component = fixture.componentInstance;
  });

  it('should create the app', () => {
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  });

  it(`should have as title 'battleshipclient'`, () => {
    const app = fixture.debugElement.componentInstance;
    expect(app.title).toEqual('battleshipclient');
  });

  it('should render title in a h1 tag', () => {
    fixture.detectChanges();
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelector('h1').textContent).toContain('Welcome to battleshipclient!');
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
