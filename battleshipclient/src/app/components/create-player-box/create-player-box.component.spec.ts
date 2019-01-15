import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreatePlayerBoxComponent } from './create-player-box.component';

describe('CreatePlayerBoxComponent', () => {
  let component: CreatePlayerBoxComponent;
  let fixture: ComponentFixture<CreatePlayerBoxComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreatePlayerBoxComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreatePlayerBoxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
