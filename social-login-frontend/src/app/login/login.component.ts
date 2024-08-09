import {
  Component,
  ElementRef,
  ViewChild,
  Renderer2,
  AfterViewInit,
} from '@angular/core';

import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { environment } from '../../environments/environment.development';

@Component({
  selector: 'login-root',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './login.component.html',
})
export class LoginComponent implements AfterViewInit {
  // ViewChild decorator to get a reference to the Google Sign-In button element
  @ViewChild('googleButton') gbutton: ElementRef = new ElementRef({});
  
  // Injecting Renderer2 for DOM manipulation
  constructor(private renderer: Renderer2) {}

  // Lifecycle hook that is called after the component's view has been fully initialized
  ngAfterViewInit() {
    this.addGoogleSignInScript();
  }

  // Method to add the Google Sign-In script to the document
  addGoogleSignInScript(): void {
    const script = this.renderer.createElement('script');
    script.src = 'https://accounts.google.com/gsi/client';
    script.async = true;
    script.defer = true;
    script.onload = () => this.initializeGoogleSignIn();
    this.renderer.appendChild(document.body, script);
  }

  // Method to initialize Google Sign-In after the script is loaded
  initializeGoogleSignIn(): void {
    const clientId = environment.googleClientId;
    // Initialize Google Sign-In with the client ID and other options
    // @ts-ignore
    window.google.accounts.id.initialize({
      client_id: clientId,
      itp_support: true,
      ux_mode: 'popup',
      context: 'signin',
      callback: this.handleCredentialResponse.bind(this),
    });
    // Render the Google Sign-In button
    // @ts-ignore
    window.google.accounts.id.renderButton(this.gbutton.nativeElement, {
      type: 'standard',
      theme: 'filled_blue',
      size: 'large',
      shape: 'pill',
    });
    // Prompt the user to sign in
    // @ts-ignore
    window.google.accounts.id.prompt();
  }

  // Callback method to handle the credential response from Google Sign-In
  handleCredentialResponse(response: any): void {
    window.location.href = `${environment.loginUri}${response.credential}`;
  }
}