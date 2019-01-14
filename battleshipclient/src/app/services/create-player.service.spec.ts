import { TestBed } from '@angular/core/testing';

import { CreatePlayerService } from './create-player.service';

describe('CreatePlayerService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CreatePlayerService = TestBed.get(CreatePlayerService);
    expect(service).toBeTruthy();
  });
});
