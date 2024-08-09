import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-login-callback',
  standalone: true,
  imports: [],
  templateUrl: './login-callback.component.html',
})
export class LoginCallbackComponent implements OnInit {
  accessToken: string = '';
  private readonly activatedRoute = inject(ActivatedRoute);
  private readonly router = inject(Router);

  ngOnInit(): void {
    // get the access token from the query params
    this.accessToken = this.activatedRoute.snapshot.queryParams['accessToken'];
    // if the access token is present, store it in the local storage and navigate to the home page
    if (this.accessToken) {
      localStorage.setItem('accessToken', this.accessToken);
      this.router.navigate(['/home']);
    } else {
      this.router.navigate(['/login/failure']);
      console.error('Tokens are missing');
    }
  }
}
