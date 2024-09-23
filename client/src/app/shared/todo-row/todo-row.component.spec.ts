import { ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { TodoRowComponent } from './todo-row.component';
import { DebugElement } from '@angular/core';
import { getFakeTodoList } from '../../../../faker-object-maker';
import { Todo } from '../../models/todo';
import { Priority } from '../../enums/priority';
import { TodoRow } from '../../interfaces/todo-row';
import { TodoMapper } from '../../mappers/todo-mapper';

describe('TodoRowComponent', () => {
  let component: TodoRowComponent;
  let fixture: ComponentFixture<TodoRowComponent>;
  let el : DebugElement;
  let todoList : Todo[];

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TodoRowComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TodoRowComponent);
    component = fixture.componentInstance;
    el = fixture.debugElement;
    todoList = getFakeTodoList(10);
    fixture.detectChanges();
  });

  it('should create', () => {
    //GIVEN
    const todo:Todo = todoList[0];
    const todoRow:TodoRow = TodoMapper.mapTodoToTodoRow(todo);

    //WHEN
    component.todoRow = todoRow;
    fixture.detectChanges()
  

    //THEN
    expect(component).toBeTruthy();
  });

  it('should display all TodoRow data correctly with property binding',()=>{
    //GIVEN
    const h3Title : HTMLElement = el.query(By.css('h3')).nativeElement;
    const pDescription : HTMLElement = el.query(By.css('p')).nativeElement;
    const dateSpan : HTMLElement = el.query(By.css('#deadLineDate')).nativeElement;
    const prioritySpan : HTMLElement = el.query(By.css('#priority > span')).nativeElement;


    //WHEN
    component.todoRow = {title: "Todo1", description: "blahblah", deadLineDate: '22/05/2024',priority: Priority.Low}
    fixture.detectChanges()


    //THEN
    expect(h3Title.textContent).toEqual(component.todoRow.title)
    expect(pDescription.textContent).toEqual(component.todoRow.description)
    expect(dateSpan.textContent).toEqual(component.todoRow.deadLineDate)
    expect(prioritySpan.textContent).toEqual(component.todoRow.priority)


  })

});
