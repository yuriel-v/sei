import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { TestService } from 'src/app/loader/test.service';

@Component({
  selector: 'sei-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  constructor(private testService:TestService) { }


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
  }

}
