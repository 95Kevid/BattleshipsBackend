import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShipPlacerUIComponent } from './ship-placer-u-i.component';
import {By} from '@angular/platform-browser';
import {DebugElement} from '@angular/core';

describe('ShipPlacerUIComponent', () => {
  let component: ShipPlacerUIComponent;
  let fixture: ComponentFixture<ShipPlacerUIComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ShipPlacerUIComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShipPlacerUIComponent);
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
