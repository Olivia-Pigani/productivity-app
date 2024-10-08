import { Component, Input } from '@angular/core';
import { TodoRow } from '../../interfaces/todo-row';

@Component({
  selector: 'app-todo-row',
  standalone: true,
  imports: [],
  templateUrl: './todo-row.component.html',
  styleUrl: './todo-row.component.css'
})
export class TodoRowComponent {
 @Input() todoRow!:TodoRow;
}
