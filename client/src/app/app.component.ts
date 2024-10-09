import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { NavbarComponent } from "./shared/navbar/navbar.component";
import { HomepageComponent } from "./homepage/homepage.component";
import { ModalComponent } from "./shared/modal/modal.component";
import { FooterComponent } from "./shared/footer/footer.component";



@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, NavbarComponent, HomepageComponent, ModalComponent, FooterComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'TDD_MyTodos';
}
