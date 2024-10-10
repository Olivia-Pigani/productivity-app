import { Component, Input, OnInit } from '@angular/core';
import { TodoRow } from '../../interfaces/todo-row';
import { Priority } from '../../enums/priority';
import { SlicePipe } from '@angular/common'
import { FormsModule } from '@angular/forms';
import { NgIf } from '@angular/common';


@Component({
  selector: 'app-todo-row',
  standalone: true,
  imports: [SlicePipe, FormsModule, NgIf],
  templateUrl: './todo-row.component.html',
  styleUrl: './todo-row.component.css'
})
export class TodoRowComponent implements OnInit {
 @Input() todoRow!:TodoRow;
 priorityColour! : 'red' | 'green' | 'yellow';
 priority = Priority;

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
}
