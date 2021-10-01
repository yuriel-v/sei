import { Component, OnInit } from '@angular/core';
import { MatIconRegistry } from '@angular/material/icon';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'sei-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.scss']
})
export class FooterComponent implements OnInit {

  constructor(private iconService:MatIconRegistry, private domSanitizer:DomSanitizer) {
    this.iconService.addSvgIcon("gitHub", this.domSanitizer.bypassSecurityTrustResourceUrl("../../assets/github-icon.svg"))
   }

  ngOnInit(): void {
  }

}
