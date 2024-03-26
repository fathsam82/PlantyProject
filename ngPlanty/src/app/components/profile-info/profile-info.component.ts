import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PaymentData } from 'src/app/models/payment-data';
import { PaymentDataService } from 'src/app/services/payment-data.service';

@Component({
  selector: 'app-profile-info',
  templateUrl: './profile-info.component.html',
  styleUrl: './profile-info.component.css',
})
export class ProfileInfoComponent implements OnInit {
  paymentDataList: PaymentData[] | undefined;
  searchPaymentDataQuery: string = '';

  constructor(
    private paymentDataService: PaymentDataService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit() {
    this.getPaymentDataByUsername();
  }

  getPaymentDataByUsername() {
    this.paymentDataService.getPaymentDataByUsername().subscribe({
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
