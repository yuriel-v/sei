import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'sei-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit {

  constructor() { }

  @Input() opened:boolean = false;
  ngOnInit(): void {
  }

}
