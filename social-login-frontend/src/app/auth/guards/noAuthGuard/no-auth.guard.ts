import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

export const noAuthGuard: CanActivateFn = (route, state) => {
  const token = localStorage.getItem('accessToken');
  const router = inject(Router);
  // If the token is present, redirect to the home page.
  if (token) {
    router.navigate(['/home']);
    return false;
  }
  return true;
};
