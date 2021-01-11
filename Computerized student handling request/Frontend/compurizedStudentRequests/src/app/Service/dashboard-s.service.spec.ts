import { TestBed } from '@angular/core/testing';

import { DashboardSService } from './dashboard-s.service';

describe('DashboardSService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DashboardSService = TestBed.get(DashboardSService);
    expect(service).toBeTruthy();
  });
});
