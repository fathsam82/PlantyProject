import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MockComponentComponent } from './mock-component.component';

describe('MockComponentComponent', () => {
  let component: MockComponentComponent;
  let fixture: ComponentFixture<MockComponentComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MockComponentComponent]
    });
    fixture = TestBed.createComponent(MockComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
