import { Component, OnInit } from '@angular/core';
import { TodoRowComponent } from '../shared/todo-row/todo-row.component';
import { TodoService } from '../services/todo/todo.service';
import { TodoRow } from '../interfaces/todo-row';
import { CommonModule } from '@angular/common'
import { TodoMapper } from '../mappers/todo-mapper';
import { Todo } from '../models/todo';
import { TimeSpace } from '../enums/time-space';
import {  WritableSignal, signal, Signal } from '@angular/core';



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

  constructor(private todoService: TodoService){}

  ngOnInit(): void {
    this.getAllTocomeTodosRow();
  }

  getAllTocomeTodosRow(){
    this.todoService.getTodosByTimeSpace(this.time).subscribe((data)=>{
      const todos : Todo[] = TodoMapper.mapTodoDtoListToTodoList(data);
       this.tocomeTodos.set(TodoMapper.mapTodoListToTodoRowList(todos));
     })
  }

}
