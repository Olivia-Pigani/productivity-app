import { Component, Input, inject } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { TocomeListComponent } from '../tocome-list/tocome-list.component';
import { TodayListComponent } from '../today-list/today-list.component';
import { TodoService } from '../services/todo.service';


@Component({
  selector: 'app-homepage',
  standalone: true,
  imports: [RouterOutlet],
  templateUrl: './homepage.component.html',
  styleUrl: './homepage.component.css'
})
export class HomepageComponent{
  
  h1Value : string = '';

  todoService: TodoService = inject(TodoService);

  listenToChildComponent(childComponent : TodayListComponent | TocomeListComponent){
    setTimeout(()=>{ // make it happen after first component cycle 
      this.h1Value = childComponent.title;
    })
  }

}
