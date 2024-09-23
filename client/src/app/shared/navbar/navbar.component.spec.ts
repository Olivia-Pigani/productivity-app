import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter } from '@angular/router'
import { NavbarComponent } from './navbar.component';
import { DebugElement } from '@angular/core';
import { By } from '@angular/platform-browser';

describe('NavbarComponent', () => {
  let component: NavbarComponent;
  let fixture: ComponentFixture<NavbarComponent>;
  let el: DebugElement;
  let navBurgerButton : HTMLElement;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NavbarComponent],
      providers: [provideRouter([])]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NavbarComponent);
    component = fixture.componentInstance;
    el = fixture.debugElement;
    navBurgerButton = el.query(By.css('.burger.btn')).nativeElement;
    fixture.detectChanges();
  });



  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should change the burger button state when clicking on it ',()=>{
    //GIVEN
    navBurgerButton;

    //WHEN1
    navBurgerButton.click();
    fixture.detectChanges();

    //THEN1
    expect(component.isMenuOpen).toBeTrue();

    //WHEN2
    navBurgerButton.click();
    fixture.detectChanges();

    //THEN2
    expect(component.isMenuOpen).toBeFalse();
  })

  it('should show burger icon and hide close icon when isMenuOpen is false',()=>{
    //GIVEN
    fixture.detectChanges()

    //WHEN
    fixture.detectChanges()
    const burgerBtnIcon : DebugElement = el.query(By.css('.burger.open'));
    const closeBtnIcon : DebugElement = el.query(By.css('.burger.close'));

    //THEN
    expect(burgerBtnIcon).toBeTruthy()
    expect(closeBtnIcon).toBeFalsy()
  })

  it('should hide burger icon and show close icon when isMenuOpen is true',()=>{
    //GIVEN
    component.isMenuOpen = true

    //WHEN
    fixture.detectChanges()
    const burgerBtnIcon : DebugElement = el.query(By.css('.burger.open'));
    const closeBtnIcon : DebugElement = el.query(By.css('.burger.close'));

    //THEN
    expect(burgerBtnIcon).toBeFalsy()
    expect(closeBtnIcon).toBeTruthy()
  })

});
