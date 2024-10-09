import { ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { HomepageComponent } from './homepage.component';
import { DebugElement } from '@angular/core';
import { provideRouter } from '@angular/router';
import { provideHttpClient } from '@angular/common/http';
import { TodoService } from '../services/todo/todo.service';


describe('HomepageComponent', () => {
  let component: HomepageComponent;
  let fixture: ComponentFixture<HomepageComponent>;
  let el : DebugElement;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HomepageComponent],
      providers:[
    provideRouter([{path:"homepage/today", component: HomepageComponent},
    {path:'homepage/tocome',component:HomepageComponent}]),
     provideHttpClient(),
     TodoService]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HomepageComponent);
    component = fixture.componentInstance;
    el = fixture.debugElement;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it("should show by default '' by default in the h1",async()=>{
    //GIVEN
    const h1Element : HTMLElement = el.query(By.css('h1')).nativeElement;

    //WHEN
    fixture.isStable();

    //THEN
    expect(h1Element.textContent).toEqual("");
  })


});
