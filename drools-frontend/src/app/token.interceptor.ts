import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {
  constructor() {}

  intercept(
    request: HttpRequest<unknown>,
    next: HttpHandler
  ): Observable<HttpEvent<unknown>> {
    if (sessionStorage.getItem('token') !== '') {
      const newRequest = request.clone({
        headers: request.headers.set(
          'Authorization',
          'Bearer ' + sessionStorage.getItem('token')!
        ),
      });
      return next.handle(newRequest);
    }
    return next.handle(request);
  }
}
