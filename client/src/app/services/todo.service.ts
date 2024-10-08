import { Injectable, OnInit, Signal, WritableSignal, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Todo } from '../models/todo';
import { Observable } from 'rxjs';
import { TimeSpace } from '../enums/time-space';
import { TodoRow } from '../interfaces/todo-row';
import { TodoMapper } from '../mappers/todo-mapper';
import { TodoDto } from '../dtos/todo-dto';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TodoService {
  baseUrl : string = "http://localhost:8585/api"

  constructor(private http: HttpClient) {}

  getTodosByTimeSpace(time: TimeSpace): Observable<TodoDto[]>{
    console.log("call is made fetch")
    return this.http.get<TodoDto[]>(`${this.baseUrl}/todos/timespace/${time}`);
  }
}


