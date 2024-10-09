import { Component, TemplateRef } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { TodayListComponent } from '../today-list/today-list.component';
import { TocomeListComponent } from '../tocome-list/tocome-list.component';
import { ModalComponent } from "../shared/modal/modal.component";
import { FooterComponent } from "../shared/footer/footer.component";


@Component({
  selector: 'app-homepage',
  standalone: true,
  imports: [RouterOutlet, ModalComponent, FooterComponent],
  templateUrl: './homepage.component.html',
  styleUrl: './homepage.component.css'
})
export class HomepageComponent{
  
  h1Value : string = '';

  listenToChildComponent(childComponent : TodayListComponent | TocomeListComponent){
    setTimeout(()=>{ // make it happen after first component cycle 
      this.h1Value = childComponent.title;
    })
  }

  openModal(customTemplate: TemplateRef<any>){
    console.log("modal opened")
  }

}
