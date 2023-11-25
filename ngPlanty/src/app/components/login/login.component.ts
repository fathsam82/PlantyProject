import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  constructor(
    private auth: AuthService,
    private router: Router
  ){}

  loginUser: User = new User();

  login(user: User) {
    console.log('Loggin in:');
    console.log(user);
  }

}
