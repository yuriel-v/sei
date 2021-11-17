import { Component, Input, OnInit } from '@angular/core';

import { Assignment } from '../model/Assignment';
import { Subject } from '../model/Subject';

@Component({
  selector: 'app-materia',
  templateUrl: './materia.component.html',
  styleUrls: ['./materia.component.scss']
})
export class MateriaComponent implements OnInit {


  @Input() materiaOpened:boolean = false;

  @Input() subject:Subject = {id:'undefined', name:'undefined', status:'undefined', limite:new Date(), nota:0};
  
  @Input() exams:Assignment[] = []

  constructor() { }

  displayedColumns: string[] = ['name', 'limite', 'status', 'nota'];

  ngOnInit(): void {
  }

}
