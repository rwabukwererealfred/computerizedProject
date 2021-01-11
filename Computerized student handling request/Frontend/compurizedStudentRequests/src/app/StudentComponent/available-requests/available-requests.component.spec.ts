import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AvailableRequestsComponent } from './available-requests.component';

describe('AvailableRequestsComponent', () => {
  let component: AvailableRequestsComponent;
  let fixture: ComponentFixture<AvailableRequestsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AvailableRequestsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AvailableRequestsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
