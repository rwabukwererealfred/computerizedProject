import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TranscriptRequestComponent } from './transcript-request.component';

describe('TranscriptRequestComponent', () => {
  let component: TranscriptRequestComponent;
  let fixture: ComponentFixture<TranscriptRequestComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TranscriptRequestComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TranscriptRequestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
