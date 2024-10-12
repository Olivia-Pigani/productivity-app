import { Component, Injector, OnInit } from '@angular/core';
import { TodoRowComponent } from '../shared/todo-row/todo-row.component';
import { TodoService } from '../services/todo/todo.service';
import { TodoRow } from '../interfaces/todo-row';
import { CommonModule } from '@angular/common'
import { TodoMapper } from '../mappers/todo-mapper';
import { Todo } from '../models/todo';
import { TimeSpace } from '../enums/time-space';
import {  WritableSignal, signal, Signal } from '@angular/core';
import { Observable, delay, map, startWith } from 'rxjs';
import { toSignal } from '@angular/core/rxjs-interop';
import { Priority } from '../enums/priority';
import { runInInjectionContext, inject } from '@angular/core';



@Component({
  selector: 'app-tocome-list',
  standalone: true,
  imports: [TodoRowComponent, CommonModule],
  templateUrl: './tocome-list.component.html',
  styleUrl: './tocome-list.component.css'
})
export class TocomeListComponent  implements OnInit {
  title:string = "Tasks to come";
  readonly time: TimeSpace = TimeSpace.Future;
  tocomeTodos: WritableSignal<TodoRow[]> = signal<TodoRow[]>([]);
  readonly tocomeTodosReadOnly: Signal<TodoRow[]> = this.tocomeTodos.asReadonly();

  constructor(private todoService: TodoService, private injector: Injector){}

  ngOnInit(): void {
    this.getAllTocomeTodosRow();
  }

  getAllTocomeTodosRow(){
    this.todoService.getTodosByTimeSpace(this.time).subscribe((data)=>{
      const todos : Todo[] = TodoMapper.mapTodoDtoListToTodoList(data);
       this.tocomeTodos.set(TodoMapper.mapTodoListToTodoRowList(todos));
       console.log("getalltodos => ", todos)
     })
  }
 
 changeTodoStatus(todoRowToUpdate:TodoRow){

  let dataSignal: Signal<TodoRow>;

  runInInjectionContext(this.injector, () => {
    const dataObservable: Observable<TodoRow> = this.todoService.updateTodoStatus(todoRowToUpdate.id, !todoRowToUpdate.isDone)
      .pipe(
        map(todoDto => TodoMapper.mapTodoDtoToTodoRow(todoDto)),
        startWith({
          id: 25,
          title: "",
          description: "",
          deadLineDate: new Date().toLocaleString(),
          priority: Priority.High,
          isDone: false
        })
      );

    dataSignal = toSignal(dataObservable) as Signal<TodoRow>;
  });

  this.tocomeTodos.update(todos => todos.map(todo => todo.id === dataSignal()!.id ? 
    {
      id: dataSignal()!.id,
      description: dataSignal()!.description,
      title: dataSignal()!.title,
      priority: dataSignal()!.priority,
      deadLineDate: dataSignal()!.deadLineDate,
      isDone: dataSignal()!.isDone
    } : todo
  ));
}
}