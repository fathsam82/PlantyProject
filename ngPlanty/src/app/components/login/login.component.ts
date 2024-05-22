import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { ConfirmationDialogComponent } from 'src/app/confirmation-dialog/confirmation-dialog.component';
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
    private router: Router,
    private dialog: MatDialog
  ){}

  loginUser: User = new User();

  login(user: User) {
    console.log('Logging in:');
    console.log(user);

    if (!user.username || !user.password) {
      this.dialog.open(ConfirmationDialogComponent, {
        data: {
          title: 'Login Error',
          errorMessage: 'Please correctly enter both the username and password.'
        }
      });
      return;
    }

    this.auth.login(user.username, user.password).subscribe({
      next: (loggedInUser) => {
        this.router.navigateByUrl('/plants');
      },
      error: (problem) => {
        console.error('LoginComponent.login(): Error logging in user:');
        console.error(problem);
        this.dialog.open(ConfirmationDialogComponent, {
          data: {
            title: 'Login Error',
            errorMessage: 'Incorrect username or password. Please try again.'
          }
        });
      }
    });
  }
}
