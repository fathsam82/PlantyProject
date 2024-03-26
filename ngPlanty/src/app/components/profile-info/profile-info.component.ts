import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PaymentData } from 'src/app/models/payment-data';
import { PaymentDataService } from 'src/app/services/payment-data.service';

@Component({
  selector: 'app-profile-info',
  templateUrl: './profile-info.component.html',
  styleUrl: './profile-info.component.css',
})
export class ProfileInfoComponent {
  paymentDataList: PaymentData[] | undefined;
  searchPaymentDataQuery: string = '';

  constructor(
    private paymentDataService: PaymentDataService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) {}

  // ngOnInit() {
  //   this.authService.getLoggedInUser().subscribe(user => {
  //     if (user && user.username) {
  //       this.searchPaymentDataQuery = user.username; // Assuming the user object has a username property
  //       this.getPaymentDataByUsername();
  //     }
  //   });
  // }


  getPaymentDataByUsername() {
    if (this.searchPaymentDataQuery.trim()) {
      this.paymentDataService
        .getPaymentDataByUsername(this.searchPaymentDataQuery)
        .subscribe({
          next: (paymentDataList) => {
            console.log(paymentDataList);
            this.paymentDataList = paymentDataList;
          },
          error: (sometingBad) => {
            console.error(
              'ProfileInfoComponent.getPaymentDataByUsername: error loading paymentData'
            );
            console.log(sometingBad);
            console.log(this.paymentDataList);
          },
        });
    }
  }
}
