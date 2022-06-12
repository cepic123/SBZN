import { TestBed } from '@angular/core/testing';

import { TopListService } from './top-list.service';

describe('RecommendationsService', () => {
  let service: TopListService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TopListService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
