import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {CreateGameBoxComponent} from './create-game-box.component';

describe('CreateGameBoxComponent', () => {
  let component: CreateGameBoxComponent;
  let fixture: ComponentFixture<CreateGameBoxComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [CreateGameBoxComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateGameBoxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
