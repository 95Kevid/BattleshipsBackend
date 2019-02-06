import { TestBed, inject } from '@angular/core/testing';
import { provideMockActions } from '@ngrx/effects/testing';
import { Observable } from 'rxjs';

import { ShipEffects } from './ship.effects';

describe('ShipEffects', () => {
  let actions$: Observable<any>;
  let effects: ShipEffects;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        ShipEffects,
        provideMockActions(() => actions$)
      ]
    });

    effects = TestBed.get(ShipEffects);
  });

  it('should be created', () => {
    expect(effects).toBeTruthy();
  });
});
