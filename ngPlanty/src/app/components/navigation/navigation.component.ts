// import { Component } from '@angular/core';
// import { MatDialog } from '@angular/material/dialog';
// import { Router } from '@angular/router';
// import { ConfirmationDialogComponent } from 'src/app/confirmation-dialog/confirmation-dialog.component';
// import { AuthService } from 'src/app/services/auth.service';

// @Component({
//   selector: 'app-navigation',
//   templateUrl: './navigation.component.html',
//   styleUrl: './navigation.component.css',
// })
// export class NavigationComponent {
//   isCollapsed: boolean = false;

//   constructor(
//     private authService: AuthService,
//     private router: Router,
//     private dialog: MatDialog
//   ) {}

//   checkLogin() {
//     return this.authService.checkLogin();
//   }

//   openLogoutDialog() {
//     const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
//       data: {
//         title: 'Confirm Logout',
//         message: 'Are you sure you want to log out?',
//       },
//     });
//     dialogRef.afterClosed().subscribe((result) => {
//       if (result) {
//         this.logout();
//       }
//     });
//   }

//   logout() {
//     console.log('Logging out.');
//     this.authService.logout();
//     this.router.navigateByUrl('/plants');
//   }
// }




import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { ConfirmationDialogComponent } from 'src/app/confirmation-dialog/confirmation-dialog.component';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css'],
})
export class NavigationComponent {
  isCollapsed: boolean = false;

  constructor(
    private authService: AuthService,
    private router: Router,
    private dialog: MatDialog
  ) {}

  checkLogin() {
    return this.authService.checkLogin();
  }

  openLogoutDialog() {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      data: {
        title: 'Confirm Logout',
        message: 'Are you sure you want to log out?',
      },
    });
    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.logout();
      }
    });
  }

  logout() {
    console.log('Logging out.');
    this.authService.logout();
    this.router.navigateByUrl('/plants');
  }
}
