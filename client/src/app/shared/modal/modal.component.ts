import { Component, ElementRef, EmbeddedViewRef, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-modal',
  standalone: true,
  imports: [],
  templateUrl: './modal.component.html',
  styleUrl: './modal.component.css'
})
export class ModalComponent {

@Input() formTitle? : string= " ";

@Output() closeEvent = new EventEmitter<void>();
@Output() submitEvent = new EventEmitter<void>();

constructor(private elementRef: ElementRef){}

close(): void{
  this.elementRef.nativeElement.remove();
  this.closeEvent.emit();
}

submit(): void{
this.elementRef.nativeElement.remove();
this.submitEvent.emit();
}


}
