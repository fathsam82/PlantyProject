import { TestBed } from '@angular/core/testing';

import { CountryRestService } from './country-rest.service';

describe('CountryRestService', () => {
  let service: CountryRestService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CountryRestService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
