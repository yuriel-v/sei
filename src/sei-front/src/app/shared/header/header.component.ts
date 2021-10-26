import { Component, EventEmitter, OnInit, Output, ViewEncapsulation } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { AuthService } from 'src/app/login/auth.service';

@Component({
  selector: 'sei-header',
  templateUrl: './header.component.html',
  encapsulation: ViewEncapsulation.None,
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {



  isLogged:boolean = false;


  constructor(private authService:AuthService) {
   }

  sidebarOpened = false;

  @Output() openSidebarEvent = new EventEmitter<boolean>();


  sidebarToggle() { 
    this.openSidebarEvent.emit(!this.sidebarOpened)
    this.sidebarOpened = !this.sidebarOpened;
  }

  testGet() { 
    this.testService.getTest().subscribe();
  }
  ngOnInit(): void {
    if (this.authService.isLogged()) { 
      this.isLogged = true;
      
    }
    }

}
