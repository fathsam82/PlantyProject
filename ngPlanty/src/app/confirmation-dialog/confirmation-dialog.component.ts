import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router } from '@angular/router';

@Component({
  selector: 'app-confirmation-dialog',
  templateUrl: './confirmation-dialog.component.html',
})
export class ConfirmationDialogComponent {
  constructor(@Inject(MAT_DIALOG_DATA) public data: any, private router: Router) {}

  get title(){
    return this.data.title || 'Confirmation';
  }

  get message(){
    return this.data.message || 'Are you sure?';
  }

  get linkText() {
    return this.data.linkText || null;
  }

  get linkRoute() {
    return this.data.linkRoute || null;
  }

  navigateToLink() {
    if (this.linkRoute) {
      this.router.navigate([this.linkRoute]);
    }
  }
}
