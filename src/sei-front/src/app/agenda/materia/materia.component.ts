import { Component, Input, OnInit } from '@angular/core';

import { Assignment } from '../model/Assignment';
import { AssignmentStatus } from '../model/Assignment-Status';
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

  getAssignmentStatus(assignment:Assignment) { 
    console.log(assignment.status)
    if (assignment.status == "PENDING") { 
      return AssignmentStatus.PENDING
    }
    else if (assignment.status == "PENDING_OK") { 
      return AssignmentStatus.PENDING_OK
    }
    else return AssignmentStatus.OK
  }
}
