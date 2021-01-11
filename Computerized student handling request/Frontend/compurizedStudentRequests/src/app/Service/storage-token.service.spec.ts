import { TestBed } from '@angular/core/testing';

import { StorageTokenService } from './storage-token.service';

describe('StorageTokenService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: StorageTokenService = TestBed.get(StorageTokenService);
    expect(service).toBeTruthy();
  });
});
