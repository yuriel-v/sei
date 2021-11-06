import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})

export class AuthService {


  constructor(private http:HttpClient, private router:Router) { }

  login(user:User) { 
    console.log(user.username);
    console.log(user.password)
    this.http.post("url/to/login", user, {observe:'response'}).subscribe(response => { 
      console.log(response);
    })
    localStorage.setItem('isLoggedIn', 'true');
    this.router.navigate(['/']).then(() => { 
      window.location.reload()
    })

  }

  isLogged() {
    return localStorage.getItem('isLoggedIn');
  }

  
}
