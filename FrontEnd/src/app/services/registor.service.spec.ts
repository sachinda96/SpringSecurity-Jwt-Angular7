import { TestBed } from '@angular/core/testing';

import { RegistorService } from './registor.service';

describe('RegistorService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: RegistorService = TestBed.get(RegistorService);
    expect(service).toBeTruthy();
  });
});
