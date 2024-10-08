import { Component, EventEmitter, Input, Output, Signal, WritableSignal, signal } from '@angular/core';
import { TodoRowComponent } from "../shared/todo-row/todo-row.component";
import { TodoRow } from '../interfaces/todo-row';
import { TodoService } from '../services/todo.service';
import { OnInit } from '@angular/core';
import { TimeSpace } from '../enums/time-space';
import { TodoMapper } from '../mappers/todo-mapper';
import { Todo } from '../models/todo';
import { getFakeTodoList } from '../../../faker-object-maker';
import { CommonModule } from '@angular/common'; 


@Component({
  selector: 'app-today-list',
  standalone: true,
  imports: [TodoRowComponent,CommonModule],
  templateUrl: './today-list.component.html',
  styleUrl: './today-list.component.css'
})
export class TodayListComponent implements OnInit{
title:string = "Today's tasks";
readonly time : TimeSpace = TimeSpace.Present;
todayTodos: WritableSignal<TodoRow[]> = signal<TodoRow[]>([]);
readonly todayTodosReadOnly: Signal<TodoRow[]> = this.todayTodos.asReadonly();

constructor(private todoService: TodoService){}

ngOnInit(): void {
  this.getAllTodayTodoRowList();
}

getAllTodayTodoRowList(){
this.todoService.getTodosByTimeSpace(this.time).subscribe((data)=>{
  const todos: Todo[] = TodoMapper.mapTodoDtoListToTodoList(data);
  this.todayTodos.set(TodoMapper.mapTodoListToTodoRowList(todos));
  console.log("fetch")
})
}

}
