import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StaffAccountComponent } from './staff-account.component';

describe('StaffAccountComponent', () => {
  let component: StaffAccountComponent;
  let fixture: ComponentFixture<StaffAccountComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StaffAccountComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StaffAccountComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
