import { DOCUMENT } from '@angular/common';
import { Inject, Injectable, Injector, TemplateRef, ViewContainerRef, EmbeddedViewRef, ComponentRef} from '@angular/core';
import { ModalComponent } from '../../shared/modal/modal.component';
import { Subject } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class ModalService {

  private modalNotifier?: Subject<string>

  constructor(
    private injector: Injector,
    @Inject(DOCUMENT) private document : Document,
    ) { }

    open(content: TemplateRef<any>,vcr: ViewContainerRef, options?: {title?: string } ) {

      const contentViewRef : EmbeddedViewRef<any> = content.createEmbeddedView(null);
      const modalComponent : ComponentRef<ModalComponent> = vcr.createComponent(ModalComponent, {
        injector: this.injector, 
        projectableNodes: [[...contentViewRef.rootNodes]] 
      });

      modalComponent.instance.formTitle = options?.title;
      modalComponent.instance.closeEvent.subscribe(() => this.closeModal());
      modalComponent.instance.submitEvent.subscribe(() => this.submitModal());

      modalComponent.hostView.detectChanges();
      this.document.body.appendChild(modalComponent.location.nativeElement);

      this.modalNotifier = new Subject();
      return this.modalNotifier?.asObservable();
    }
  
    closeModal() {
      this.modalNotifier?.complete();
    }
  
    submitModal() {
      this.modalNotifier?.next('confirm');
      this.closeModal();
    }
  }


