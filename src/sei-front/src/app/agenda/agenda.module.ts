import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MatExpansionModule} from '@angular/material/expansion';
import { AgendaRoutingModule } from './agenda-routing.module';
import { AgendaComponent } from './agenda.component';
import { MateriaComponent } from './materia/materia.component';
import {MatTableModule} from '@angular/material/table';
import {MatDividerModule} from '@angular/material/divider';


@NgModule({
  declarations: [
    AgendaComponent,
    MateriaComponent
  ],
  imports: [
    CommonModule,
    AgendaRoutingModule,
    MatExpansionModule,
    MatTableModule,
    MatDividerModule
  ]
})
export class AgendaModule { }
