import { HttpInterceptorFn } from '@angular/common/http';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  // Clone the request to add the new header.
  let newReq = req;
  const accessToken = localStorage.getItem('accessToken');

  if (accessToken) {
    // Add the Authorization header with the token.
    newReq = req.clone({
      headers: req.headers.set('Authorization', `Bearer ${accessToken}`),
    });
  }

  return next(newReq);
};
