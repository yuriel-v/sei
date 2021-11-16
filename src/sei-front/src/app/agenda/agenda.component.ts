import { Component, OnInit } from '@angular/core';
import { Subject } from './model/Subject';

@Component({
  selector: 'app-agenda',
  templateUrl: './agenda.component.html',
  styleUrls: ['./agenda.component.scss']
})
export class AgendaComponent implements OnInit {

  constructor() { }

  materias:Subject[] = [{name:"Processos Estocáticos", limite:new Date(), assignments:[], nota:9.0, status:"Aprovado"}]
  

  tabelaAgendaColuna = ["Matéria", "Limite", "Status", "Nota"]

  ngOnInit(): void {
  }

}
