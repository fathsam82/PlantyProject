import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PaymentData } from 'src/app/models/payment-data';
import { User } from 'src/app/models/user';
import { PaymentDataService } from 'src/app/services/payment-data.service';

@Component({
  selector: 'app-profile-info',
  templateUrl: './profile-info.component.html',
  styleUrls: ['./profile-info.component.css'], // Corrected typo here
})
export class ProfileInfoComponent implements OnInit {
  paymentDataList: PaymentData[] | undefined;
  searchPaymentDataQuery: string = '';
  newPaymentData: PaymentData = new PaymentData;


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
      error: (somethingBad) => {
        console.error('ProfileInfoComponent.getPaymentDataByUsername: error loading paymentData');
        console.log(somethingBad);
        console.log(this.paymentDataList);
      },
    });
  }

  createPaymentData(paymentData: PaymentData) {
    const [month, year] = paymentData.expirationDate.split('/');
    paymentData.expirationDate = `20${year}-${month}-01`;

    this.paymentDataService.createPaymentData(paymentData).subscribe({
        next: (paymentData) => {
          this.newPaymentData = new PaymentData();
          this.getPaymentDataByUsername();
          console.log(paymentData);
        },
        error: (error) => {
          console.error('Error adding paymentData to profile-info', error);
        },
      });
  }



}
