import { CanActivateFn, Router } from '@angular/router';
import { inject } from '@angular/core';
import { UserService } from '../../../service/user.service';

export const AuthGuard: CanActivateFn = (route, state) => {
  const token = localStorage.getItem('accessToken');
  const userService = inject(UserService);
  const router = inject(Router);
  // If the token is missing, redirect to the login page.
  if (!token) {
    router.navigate(['/login']);
    return false;
  }

  // If the user is missing, fetch the user.
  if (!userService.hasUser()) {
    userService.getUser().subscribe();
  }
  return true;
};
