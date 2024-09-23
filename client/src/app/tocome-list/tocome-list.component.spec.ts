import { ComponentFixture, TestBed } from '@angular/core/testing';
import {provideHttpClient} from '@angular/common/http';
import {provideHttpClientTesting} from '@angular/common/http/testing';

import { TocomeListComponent } from './tocome-list.component';

describe('TocomeListComponent', () => {
  let component: TocomeListComponent;
  let fixture: ComponentFixture<TocomeListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TocomeListComponent],
      providers: [
        provideHttpClient(),
        provideHttpClientTesting()
      ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TocomeListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
