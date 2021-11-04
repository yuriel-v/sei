import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import { AuthService } from './auth.service';
import { User } from './user';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  hide: boolean = true;
  constructor(private authService:AuthService) { }

  ngOnInit(): void {
  }

  loginForm = new FormGroup({
    username: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required])
  })

  getUsernameErrorMessage() {
    let usernameControl = this.loginForm.get('username');
    if (usernameControl?.hasError('required')) {
        return 'Insira um endereço';
    }

    if (usernameControl?.hasError('email')) {
        return 'Endereço inválido'
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
      username:this.loginForm.get('username')?.value,
      password:this.loginForm.get('password')?.value

    }
    this.authService.login(user);
  }


  redefinirSenha() { 
    console.log("Aqui vai a função de redefinir senha")
  }
}