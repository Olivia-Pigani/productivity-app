import { ChangeDetectorRef, Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { TodoRow } from '../../interfaces/todo-row';
import { Priority } from '../../enums/priority';
import { SlicePipe } from '@angular/common'
import { FormsModule } from '@angular/forms';
import { NgIf } from '@angular/common';
import { TodoService } from '../../services/todo/todo.service';
import { TodoDto } from '../../dtos/todo-dto';
import { TodoMapper } from '../../mappers/todo-mapper';
import {Signal } from '@angular/core';


@Component({
  selector: 'app-todo-row',
  standalone: true,
  imports: [SlicePipe, FormsModule, NgIf],
  templateUrl: './todo-row.component.html',
  styleUrl: './todo-row.component.css'
})
export class TodoRowComponent implements OnInit {
 @Input() todoRow!:TodoRow;
 @Output() onTodoChange = new EventEmitter<TodoRow>();
 @Output() onDeleteTodo = new EventEmitter<number>();
 priorityColour! : 'red' | 'green' | 'yellow';
 priority = Priority;

 constructor(){}

 ngOnInit(): void {
   this.initPriorityColour();
 }

 private initPriorityColour(): void{

  switch(this.todoRow.priority){
    case this.priority.High: {
      this.priorityColour = 'red';
      break;
    }
    case this.priority.Medium: {
      this.priorityColour = 'yellow'
      break;
    }
    case this.priority.Low: {
      this.priorityColour = 'green'
      break;
    }
    default: {
      this.priorityColour = 'green'
    }
  }
 }

 onChangeTodoStatus(){
  this.onTodoChange.emit(this.todoRow)
 }
 
 onDeleteTodoWithId(){
  console.log("delete emitting")
  this.onDeleteTodo.emit(this.todoRow.id)
 }
}
