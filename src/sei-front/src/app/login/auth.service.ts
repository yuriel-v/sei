import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ThrowStmt } from '@angular/compiler';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { User } from './user';


const API_URL = "http://localhost:9080"

@Injectable({
  providedIn: 'root'
})

export class AuthService {

  constructor(private router:Router, private cookieService:CookieService, private http:HttpClient) { }

  login(user:User) { 
    return this.http.post(`${API_URL}/login`, user, {observe:'response'})
  }

  isLogged() {
    if (this.cookieService.get('registry')) { 
      return true
    }
    else return false;
  }

  logout() { 
    this.cookieService.delete('registry');
    this.router.navigate(['/']).then(() => { 
      window.location.reload()
    })
  }
  
}
