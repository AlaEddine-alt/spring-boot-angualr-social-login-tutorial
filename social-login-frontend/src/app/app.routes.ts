import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { LoginCallbackComponent } from './login-callback/login-callback.component';
import { HomeComponent } from './home/home.component';
import { AuthGuard } from './auth/guards/AuthGuard/auth.guard';
import { noAuthGuard } from './auth/guards/noAuthGuard/no-auth.guard';
import { LoginFailureComponent } from './login-failure/login-failure.component';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full',
  },
  {
    canActivate: [noAuthGuard],
    path: 'login',
    component: LoginComponent,
    children: [
      { path: 'callback', component: LoginCallbackComponent },
      { path: 'failure', component: LoginFailureComponent },
    ],
  },

  {
    canActivate: [AuthGuard],
    path: 'home',
    component: HomeComponent,
  },
];
