import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GameStatusBoxComponent } from './game-status-box.component';

describe('GameStatusBoxComponent', () => {
  let component: GameStatusBoxComponent;
  let fixture: ComponentFixture<GameStatusBoxComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GameStatusBoxComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GameStatusBoxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
