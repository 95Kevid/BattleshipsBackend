import { TestBed } from '@angular/core/testing';

import { CreateGameService } from './create-game.service';

describe('CreateGameService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CreateGameService = TestBed.get(CreateGameService);
    expect(service).toBeTruthy();
  });
});
