import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewsStreamComponent } from './news-stream.component';

describe('NewsStreamComponent', () => {
  let component: NewsStreamComponent;
  let fixture: ComponentFixture<NewsStreamComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewsStreamComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewsStreamComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
