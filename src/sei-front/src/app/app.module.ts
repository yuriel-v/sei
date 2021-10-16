import { NgModule } from '@angular/core';
import { BrowserModule, Title } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { SharedModule } from './shared/shared.module';
import { HomeModule } from './home/home.module';
import { LoginModule } from './login/login.module';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { LoaderInterceptorService } from './loader/loader-interceptor.service';
import { MatButtonModule } from '@angular/material/button';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    BrowserAnimationsModule,
    SharedModule,
    HomeModule,
    LoginModule,
    MatProgressBarModule,
    MatButtonModule
  ],
  providers: [Title, {provide: HTTP_INTERCEPTORS, useClass:LoaderInterceptorService,  multi:true}],
  bootstrap: [AppComponent]
})
export class AppModule { }
