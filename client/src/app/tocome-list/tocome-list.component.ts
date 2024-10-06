import { Component, OnInit } from '@angular/core';
import { TodoRowComponent } from '../shared/todo-row/todo-row.component';
import { TodoService } from '../services/todo.service';
import { TodoRow } from '../interfaces/todo-row';
import { CommonModule } from '@angular/common'


@Component({
  selector: 'app-tocome-list',
  standalone: true,
  imports: [TodoRowComponent, CommonModule],
  templateUrl: './tocome-list.component.html',
  styleUrl: './tocome-list.component.css'
})
export class TocomeListComponent implements OnInit{
  title:string = "Tasks to come";
  tocomeTodos: TodoRow[] = []

  constructor(private todoService: TodoService){}

  ngOnInit(): void {
    this.todoService.getAllTocomeTodoRowList();
    this.tocomeTodos = this.todoService.tocomeTodoRowList();
    console.log(this.tocomeTodos);
  }

}
