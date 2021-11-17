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
import {MatTooltipModule} from '@angular/material/tooltip';
import { LoginRoutingModule } from './login/login-routing.module';
import { AgendaModule } from './agenda/agenda.module';
import { AgendaRoutingModule } from './agenda/agenda-routing.module';
import { CookieService } from 'ngx-cookie-service';


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
    MatButtonModule,
    MatTooltipModule,
    NgbModule,
    LoginRoutingModule,
    AgendaModule,
    AgendaRoutingModule
  ],
  providers: [Title, {provide: HTTP_INTERCEPTORS, useClass:LoaderInterceptorService,  multi:true}, CookieService],
  bootstrap: [AppComponent]
})
export class AppModule { }
