import { Component, EventEmitter, Input, Output, WritableSignal, signal } from '@angular/core';
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
export class TodayListComponent implements OnInit {
title:string = "Today's tasks";
todayTodos: TodoRow[] = [];


constructor(private todoService: TodoService){}

ngOnInit(): void {
  this.todoService.todayTodoRowList;
}

}
