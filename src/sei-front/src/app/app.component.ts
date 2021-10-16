import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { LoaderService } from './loader/loader.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})



export class AppComponent {
  sidebarOpened = false;


  constructor(public loaderService:LoaderService, public http:HttpClient) { 

  }
  sidebarTrigger(status: boolean) {
    this.sidebarOpened = status;
  }


  getTeste() { 
    this.http.get("https://jsonplaceholder.typicode.com/comments").subscribe(x => { 
      console.log(x);
    })
  }

  title = 'Sistema de Ensino Inteligente';
}
