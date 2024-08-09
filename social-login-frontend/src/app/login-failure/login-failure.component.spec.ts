import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginFailureComponent } from './login-failure.component';

describe('LoginFailureComponent', () => {
  let component: LoginFailureComponent;
  let fixture: ComponentFixture<LoginFailureComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LoginFailureComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(LoginFailureComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
