import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AboutComponent } from './about/about.component';
import { MatButtonModule } from '@angular/material/button';


@NgModule({
  declarations: [
    AboutComponent
  ],
  imports: [
    CommonModule,
    MatButtonModule
  ],
  exports:[AboutComponent]
})
export class HomeModule { }
