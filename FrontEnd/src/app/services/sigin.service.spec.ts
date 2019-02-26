import { TestBed } from '@angular/core/testing';

import { SiginService } from './sigin.service';

describe('SiginService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SiginService = TestBed.get(SiginService);
    expect(service).toBeTruthy();
  });
});
