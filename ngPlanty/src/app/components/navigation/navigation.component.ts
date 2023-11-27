import { Component } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrl: './navigation.component.css'
})
export class NavigationComponent {

  isCollapsed: boolean = false;

  constructor(private authService: AuthService) {}

  checkLogin() {
    return this.authService.checkLogin();
  }

}
