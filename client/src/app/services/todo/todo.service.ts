import { Injectable} from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';
import { TimeSpace } from '../../enums/time-space';
import { TodoDto } from '../../dtos/todo-dto';

@Injectable({
  providedIn: 'root'
})
export class TodoService {
  baseUrl : string = "http://localhost:8585/api"

  constructor(private http: HttpClient) {}

  getTodosByTimeSpace(time: TimeSpace): Observable<TodoDto[]>{
    return this.http.get<TodoDto[]>(`${this.baseUrl}/todos/timespace/${time}`);
  }

  updateTodoStatus(todoId: number, newStatus: boolean): Observable<TodoDto>{
    return this.http.patch<TodoDto>(`${this.baseUrl}/todos/${todoId}`, newStatus);
  }

  deleteTodoById(todoId: number): Observable<TodoDto>{
    return this.http.delete<TodoDto>(`${this.baseUrl}/todos/${todoId}`);
  }

}


