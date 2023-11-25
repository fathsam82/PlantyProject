import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrl: './logout.component.css'
})
export class LogoutComponent {

  constructor(
    private auth: AuthService,
    private router: Router
  ){}

  logout(){
    console.log('Logging out.');
    this.auth.logout();
    this.router.navigateByUrl('/plants');
  }

}
