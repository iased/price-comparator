import { inject } from '@angular/core';
import { HttpInterceptorFn } from '@angular/common/http';
import { AuthService } from './auth.service';
import { Router } from '@angular/router';
import { catchError, throwError } from 'rxjs';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const auth = inject(AuthService);
  const token = auth.getToken();
  const router = inject(Router);

  const authReq = token
      ? req.clone({
          setHeaders: {
            Authorization: `Bearer ${token}`,
          },
        })
      : req;

    return next(authReq).pipe(
      catchError(err => {
        if (err.status === 401 || err.status === 403) {
          auth.logout();
          router.navigate(['/login']);
        }

        return throwError(() => err);
      })
    );
};
