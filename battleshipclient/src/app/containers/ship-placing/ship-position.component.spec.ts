import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {ShipPositionComponent} from './ship-position.component.';
import {By} from '@angular/platform-browser';
import {DebugElement} from '@angular/core';

describe('ShipPositionComponent', () => {
  let component: ShipPositionComponent;
  let fixture: ComponentFixture<ShipPositionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ShipPositionComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShipPositionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('the title should be Ship Placing', () => {
    expect(component.title).toBe('Ship Placing');

    const debug: DebugElement = fixture.debugElement.query(By.css('p'));
    expect(debug).not.toBeNull();
    // const native: HTMLElement = debug.nativeElement;
    // expect(native.textContent).toEqual('Hi');
  });

});
