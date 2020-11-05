import { TestBed } from '@angular/core/testing';

import { Luv2ShopForm2Service } from './luv2-shop-form2.service';

describe('Luv2ShopForm2Service', () => {
  let service: Luv2ShopForm2Service;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Luv2ShopForm2Service);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
