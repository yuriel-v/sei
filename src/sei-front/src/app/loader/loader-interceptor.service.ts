import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoaderService } from './loader.service';
import { finalize } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class LoaderInterceptorService implements HttpInterceptor {


  constructor(public loaderService:LoaderService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    this.loaderService.isLoading.next(true);

    //handle the request, interceptor. And please, let my progress bar know when you done, and make it disappear.
    console.log("intercepted");
    return next.handle(req).pipe(
      finalize(() => 
      this.loaderService.isLoading.next(false)))
  }

}
