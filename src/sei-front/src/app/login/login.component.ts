import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from './auth.service';
import { User } from './user';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  invalidLogin:boolean = false;
  hide: boolean = true;
  constructor(private authService:AuthService, private router:Router) { }

  ngOnInit(): void {
  }

  loginForm = new FormGroup({
    username: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required])
  })

  getUsernameErrorMessage() {
    let usernameControl = this.loginForm.get('username');
    if (usernameControl?.hasError('required')) {
        return 'Insira um usuário';
    }

    if (usernameControl?.hasError('email')) {
        return 'Usuário inválido'
    }
    return ''
  }

  getPasswordErrorMessage() {
    let passwordControl = this.loginForm.get('password');
    if (passwordControl?.hasError('required')) {
        return 'Insira uma senha';
    }
    return ''
  }

  submitLoginForm() { 
    let user:User = {
      user:this.loginForm.get('username')?.value,
      password:this.loginForm.get('password')?.value

    }
    this.authService.login(user).subscribe(result => { 
      if (result.status == 200) { 
        localStorage.setItem('isLoggedIn', 'true');
        this.router.navigate(['/']).then(() => { 
          window.location.reload()
        })
      }
    }, err => { 
      if (err.status == 401) { 
        this.invalidLogin = true
      }
    });
  }


  redefinirSenha() { 
    console.log("Aqui vai a função de redefinir senha")
  }
}