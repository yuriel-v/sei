import { Component } from '@angular/core';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})



export class AppComponent {
  sidebarOpened = false;


  sidebarTrigger(status: boolean) {
    console.log(status)
    this.sidebarOpened = status;
  }

  title = 'Sistema de Ensino Inteligente';
}
