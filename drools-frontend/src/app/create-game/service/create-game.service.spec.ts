import { TestBed } from '@angular/core/testing';

import { CreateGameService } from './create-game.service';

describe('RecommendationsService', () => {
  let service: CreateGameService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CreateGameService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
