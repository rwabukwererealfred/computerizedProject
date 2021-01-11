import { TestBed } from '@angular/core/testing';

import { SendRequestsService } from './send-requests.service';

describe('SendRequestsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SendRequestsService = TestBed.get(SendRequestsService);
    expect(service).toBeTruthy();
  });
});
