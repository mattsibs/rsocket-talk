import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MonitorWrapperComponent } from './monitor-wrapper.component';

describe('MonitorWrapperComponent', () => {
  let component: MonitorWrapperComponent;
  let fixture: ComponentFixture<MonitorWrapperComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MonitorWrapperComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MonitorWrapperComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
