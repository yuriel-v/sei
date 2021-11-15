import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { User } from './user';




@Injectable({
  providedIn: 'root'
})

export class AuthService {

  constructor(private http:HttpClient) { }

  login(user:User) { 
    return this.http.post("http://localhost:9080/login", user, {observe:'response'})
  }

  isLogged() {
    return localStorage.getItem('isLoggedIn');
  }

  
}
