import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { AgendaService } from './agenda.service';
import { Agenda } from './model/Agenda';
import { Assignment } from './model/Assignment';
import { Enrollment } from './model/Enrollment';
import { Subject } from './model/Subject';

@Component({
  selector: 'app-agenda',
  templateUrl: './agenda.component.html',
  styleUrls: ['./agenda.component.scss']
})
export class AgendaComponent implements OnInit {

  constructor(private cookieService:CookieService, private agendaService:AgendaService) { }

  noContent:boolean = false;
  materias:Subject[] = []
  exams:Assignment[] = []

  tabelaAgendaColuna = ["Matéria", "Limite", "Status", "Nota"]

  ngOnInit(): void {
    this.agendaService.getMaterias(this.cookieService.get('registry')).subscribe(result => { 
      if (result.body) {
        let agenda:Agenda = result.body
        agenda.enrollments.forEach(enrollment => { 
          this.materias.push(enrollment.subject)
          this.exams = enrollment.exams;
        })
        console.log(agenda)
        console.log(this.materias)
      }
      else {
        this.noContent = true;
      }
    })
  }

}
