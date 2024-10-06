import { Injectable, OnInit, Signal, WritableSignal, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Todo } from '../models/todo';
import { Observable } from 'rxjs';
import { TimeSpace } from '../enums/time-space';
import { TodoRow } from '../interfaces/todo-row';
import { TodoMapper } from '../mappers/todo-mapper';
import { TodoDto } from '../dtos/todo-dto';

@Injectable({
  providedIn: 'root'
})
export class TodoService implements OnInit{
  baseUrl : string = "http://localhost:8585/api"
  
  private todayTodoRowListSignal:WritableSignal<TodoRow[]> = signal<TodoRow[]>([]);
  readonly todayTodoRowList: Signal<TodoRow[]> = this.todayTodoRowListSignal.asReadonly();

  private tocomeTodoRowListSignal:WritableSignal<TodoRow[]> = signal<TodoRow[]>([]);
  readonly tocomeTodoRowList: Signal<TodoRow[]> = this.tocomeTodoRowListSignal.asReadonly();

  constructor(private http: HttpClient) {}

  // getAllTodos(): Observable<Todo[]>{
  //   return this.http.get<Todo[]>(`${this.baseUrl}/todos`);
  // }

  getTodosByTimeSpace(time: TimeSpace): Observable<TodoDto[]>{
    return this.http.get<TodoDto[]>(`${this.baseUrl}/todos/timespace/${time}`);
  }


  getAllTocomeTodoRowList(): void{
    this.getTodosByTimeSpace(TimeSpace.Future).subscribe({
      next: (todoDtos: TodoDto[]) => {
        const todos : Todo[] = TodoMapper.mapTodoDtoListToTodoList(todoDtos);
        this.tocomeTodoRowListSignal.set(TodoMapper.mapTodoListToTodoRowList(todos));
      }
    })

  }
  // getAllTodayTodoRowList(): void{
  //   this.getTodosByTimeSpace(TimeSpace.Present).subscribe({
  //     next: (todos: Todo[]) => {
  //       this.todayTodoRowListSignal.set(TodoMapper.mapTodoListToTodoRowList(todos));
  //     }
  //   })

  // }

  ngOnInit(): void {
   this.getAllTocomeTodoRowList();
   //this.getAllTodayTodoRowList();
  }

}
