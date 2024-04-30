import { TestBed } from '@angular/core/testing';

import { PlantOriginService } from './plant-origin.service';

describe('PlantOriginService', () => {
  let service: PlantOriginService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PlantOriginService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
