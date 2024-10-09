import { ComponentFixture, TestBed } from '@angular/core/testing';
import { fakeAsync } from '@angular/core/testing'
import { TodayListComponent } from './today-list.component';
import { provideHttpClient } from '@angular/common/http';
import { getFakeTodoList } from '../../../faker-object-maker';
import { Todo } from '../models/todo';
import { TodoRow } from '../interfaces/todo-row';
import { TodoMapper } from '../mappers/todo-mapper';
import { TodoService } from '../services/todo/todo.service';
import { of } from 'rxjs';
import { tick } from '@angular/core/testing'


describe('TodayListComponent', () => {
  let component: TodayListComponent;
  let fixture: ComponentFixture<TodayListComponent>;
  let todoService : TodoService;
  let serviceSpy: jasmine.Spy;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TodayListComponent],
      providers: [provideHttpClient()]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TodayListComponent);
    todoService = TestBed.inject(TodoService);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create when there is data to show', () => {
    expect(component).toBeTruthy();
  });

  it('should todayTodos be empty before ngOninit',fakeAsync(()=>{
    //GIVEN
    component.todayTodos;

    //WHEN
    

    //THEN
    expect(component.todayTodos.length).toEqual(0);
  }))


});
