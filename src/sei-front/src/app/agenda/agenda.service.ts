import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Agenda } from './model/Agenda';

const API_URL = "http://localhost:9080"


@Injectable({
  providedIn: 'root'
})
export class AgendaService {

  constructor(private http:HttpClient) { }

  getMaterias(matricula:string) { 
    return this.http.get<Agenda>(`${API_URL}/agenda?m=${matricula}`, {observe:'response'});

  }
}
