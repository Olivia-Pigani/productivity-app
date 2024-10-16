import { Component, OnInit, TemplateRef, ViewContainerRef } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { TodayListComponent } from '../today-list/today-list.component';
import { TocomeListComponent } from '../tocome-list/tocome-list.component';
import { ModalComponent } from "../shared/modal/modal.component";
import { FooterComponent } from "../shared/footer/footer.component";
import {ReactiveFormsModule, FormBuilder, Validators} from '@angular/forms';
import {FormControl, FormGroup} from '@angular/forms';
import { TodoDto } from '../dtos/todo-dto';
import { TodoRow } from '../interfaces/todo-row';
import { PostTodoDto } from '../dtos/post-todo-dto';
import { CommonModule } from '@angular/common';
import { Priority } from '../enums/priority';
import { TodoService } from '../services/todo/todo.service';



@Component({
  selector: 'app-homepage',
  standalone: true,
  imports: [RouterOutlet, ModalComponent, FooterComponent, ReactiveFormsModule, CommonModule],
  templateUrl: './homepage.component.html',
  styleUrl: './homepage.component.css'
})
export class HomepageComponent{
  
  h1Value : string = '';
  modalDisplay : 'modal opened' | 'modal closed' = 'modal closed';
  formTitle : string = '';
  errorMsg : string | null = null;

  constructor(private todoService: TodoService){}

  registerForm = new FormGroup({
    title: new FormControl('', [Validators.required]),
    deadLineDate: new FormControl('',[Validators.required]),
    priority: new FormControl('',[Validators.required]),
    description: new FormControl('',[Validators.maxLength(350)])
  })

  listenToChildComponent(childComponent : TodayListComponent | TocomeListComponent): void{
    setTimeout(()=>{ // make it happen after first component cycle 
      this.h1Value = childComponent.title;
    })
  }

  openModalForPostingTodo(): void{
    this.formTitle = 'New Todo';
    this.switchModalValue()
  }


  closeModal(): void{
    this.switchModalValue()
  }


  onSubmit(): void{
  
    if(this.registerForm.valid){

      const formData = this.registerForm.value;

      const newTodo: PostTodoDto = {
        title: formData.title ? formData.title : '',
        description: formData.description? formData.description : '',
        deadLineDate: formData.deadLineDate? new Date(formData.deadLineDate as string) : new Date(),
        priority: Priority[formData.priority?.toUpperCase() as keyof typeof Priority]
      }

      //post

      //signal

      this.switchModalValue()
      this.errorMsg = null;

    }else{
      this.errorMsg = "Please fill all inputs marked with a *, description must not be greater than 350 characters";
    }
  }

  private switchModalValue(): void{
    this.modalDisplay === 'modal opened' ? this.modalDisplay = 'modal closed' : this.modalDisplay = 'modal opened';
    this.registerForm.reset();
  }


}
