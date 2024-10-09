import { TestBed, fakeAsync,tick } from '@angular/core/testing';
import { TodoService } from './todo.service';
import { provideHttpClient } from '@angular/common/http';
import { provideHttpClientTesting } from '@angular/common/http/testing';
import { HttpTestingController } from '@angular/common/http/testing'
import { Todo } from '../models/todo';
import { getFakeTodoList } from '../../../faker-object-maker';
import { TimeSpace } from '../enums/time-space';

describe('TodoService', () => {
  let service: TodoService;
  let testingController : HttpTestingController;
  let todoFromMockedDb: Todo[];

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        provideHttpClient(), // we don't make real HttpClient call
        provideHttpClientTesting()]
    });
    service = TestBed.inject(TodoService);
    todoFromMockedDb = getFakeTodoList(15);
    testingController = TestBed.inject(HttpTestingController)
  });

  afterEach(()=>{
    testingController.verify();
  })


  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it("should get all todos after a GET http call to the back",fakeAsync(()=>{
    //GIVEN
    todoFromMockedDb;
    let httpResult : Todo[] = [];

    //WHEN
    service.getAllTodos().subscribe((todos: Todo[])=>{
      httpResult = todos;
    });

    //THEN
    const mockReq = testingController.expectOne('http://localhost:8585/api/todos')
    expect(mockReq.request.method).toBe('GET');

    mockReq.flush(todoFromMockedDb);
    tick()
    expect(httpResult).toEqual(todoFromMockedDb)
  }));

  it('should give all todos from the past',fakeAsync(()=>{
    //GIVEN
    todoFromMockedDb;
    let pastTodos : Todo[] = [];

    //WHEN
    service.getTodosByTimeSpace(TimeSpace.Past).subscribe((todos: Todo[])=>{
      pastTodos = todos;
    })

    //THEN
    const mockReq = testingController.expectOne(`http://localhost:8585/api/todos/${TimeSpace.Past}`)
    expect(mockReq.request.method).toBe('GET');
    expect(mockReq.request.params).toBeDefined;
    mockReq.flush(todoFromMockedDb);
    tick()
    expect(pastTodos).toEqual(todoFromMockedDb)
  }))



})

