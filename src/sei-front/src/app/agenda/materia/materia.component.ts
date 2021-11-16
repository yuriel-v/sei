import { Component, Input, OnInit } from '@angular/core';
import { Subject } from '../model/Subject';

@Component({
  selector: 'app-materia',
  templateUrl: './materia.component.html',
  styleUrls: ['./materia.component.scss']
})
export class MateriaComponent implements OnInit {


  @Input() materiaOpened:boolean = false;

  @Input() materia:Subject[] = []

  assignments:any[] = [{name:"AV1", limite:"17-11-2021", status:"Não feito", nota:"9.0"}]

  constructor() { }

  displayedColumns: string[] = ['name', 'limite', 'status', 'nota'];

  ngOnInit(): void {
  }

}
