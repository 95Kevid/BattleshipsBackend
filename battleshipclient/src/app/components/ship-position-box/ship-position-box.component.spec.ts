import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShipPositionBoxComponent } from './ship-position-box.component';

describe('ShipPositionBoxComponent', () => {
  let component: ShipPositionBoxComponent;
  let fixture: ComponentFixture<ShipPositionBoxComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ShipPositionBoxComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShipPositionBoxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
