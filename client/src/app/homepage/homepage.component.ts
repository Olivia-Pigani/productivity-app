import { Component, TemplateRef, ViewContainerRef } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { TodayListComponent } from '../today-list/today-list.component';
import { TocomeListComponent } from '../tocome-list/tocome-list.component';
import { ModalComponent } from "../shared/modal/modal.component";
import { FooterComponent } from "../shared/footer/footer.component";
import { ModalService } from '../services/modal/modal.service';


@Component({
  selector: 'app-homepage',
  standalone: true,
  imports: [RouterOutlet, ModalComponent, FooterComponent],
  templateUrl: './homepage.component.html',
  styleUrl: './homepage.component.css'
})
export class HomepageComponent{
  
  h1Value : string = '';

  constructor(private modalService : ModalService, private vcr: ViewContainerRef){}

  listenToChildComponent(childComponent : TodayListComponent | TocomeListComponent){
    setTimeout(()=>{ // make it happen after first component cycle 
      this.h1Value = childComponent.title;
    })
  }

  openModal(customTemplate: TemplateRef<any>){
    this.modalService
    .open(customTemplate, this.vcr ,{title: "Add a task"}) // vcr must be in component not in service to avoid circular dependancy
    .subscribe(action => {
      console.log("modal actionned",action);

    })
  }

}
