import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TestService {

  constructor(private http:HttpClient) {
    
   }

   getTest() { 
     const headers = new HttpHeaders().set('Access-Control-Allow-Origin', '*')
     return this.http.get("https://google.com.br", {headers:headers});
   }
}
