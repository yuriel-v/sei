import { Component, EventEmitter, OnInit, Output } from '@angular/core';

@Component({
  selector: 'sei-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  constructor() { }


  sidebarOpened = false;

  @Output() openSidebarEvent = new EventEmitter<boolean>();


  sidebarToggle() { 
    this.openSidebarEvent.emit(!this.sidebarOpened)
    this.sidebarOpened = !this.sidebarOpened;
  }

  ngOnInit(): void {
  }

}
