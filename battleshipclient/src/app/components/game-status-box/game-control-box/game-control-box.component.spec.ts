import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GameControlBoxComponent } from './game-control-box.component';

describe('GameControlBoxComponent', () => {
  let component: GameControlBoxComponent;
  let fixture: ComponentFixture<GameControlBoxComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GameControlBoxComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GameControlBoxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
