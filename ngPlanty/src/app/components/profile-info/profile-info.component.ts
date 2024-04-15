import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PaymentData } from 'src/app/models/payment-data';
import { ShippingAddress } from 'src/app/models/shipping-address';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { CountryRestService } from 'src/app/services/country-rest.service';
import { PaymentDataService } from 'src/app/services/payment-data.service';
import { ShippingAddressService } from 'src/app/services/shipping-address.service';

@Component({
  selector: 'app-profile-info',
  templateUrl: './profile-info.component.html',
  styleUrls: ['./profile-info.component.css'],
})
export class ProfileInfoComponent implements OnInit {
  paymentDataList: PaymentData[] | undefined;
  newPaymentData: PaymentData = new PaymentData();
  shippingAddresses: ShippingAddress[] | undefined;
  newShippingAddress: ShippingAddress = new ShippingAddress();

  countries: any[] = [];

  username: string | null = null;

  constructor(
    private paymentDataService: PaymentDataService,
    private shippingAddressService: ShippingAddressService,
    private countryRestService: CountryRestService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private authService: AuthService
  ) {}

  ngOnInit() {
    this.username = this.authService.getUsername();
    this.getPaymentDataByUsername();
    this.getShippingAddressByUsername();
    this.getCountries();
  }

  getPaymentDataByUsername() {
    this.paymentDataService.getPaymentDataByUsername().subscribe({
      next: (paymentDataList) => {
        console.log(paymentDataList);
        this.paymentDataList = paymentDataList;
      },
      error: (somethingBad) => {
        console.error(
          'ProfileInfoComponent.getPaymentDataByUsername: error loading paymentData'
        );
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

  deletePaymentData(paymentDataId: number) {
    this.paymentDataService.deletePaymentData(paymentDataId).subscribe({
      next: () => {
        console.log('Payment data deleted successfully');
        this.getPaymentDataByUsername();
      },
      error: (error) => {
        console.error('Error deleting paymentData', error);
      },
    });
  }
  getShippingAddressByUsername() {
    this.shippingAddressService.getShippingAddressByUsername().subscribe({
      next: (addresses) => {
        this.shippingAddresses = addresses;
      },
      error: (error) => {
        console.error(
          'ProfileInfoComponent.getShippingAddresses(): error loading shipping addresses',
          error
        );
      },
    });
  }

  createShippingAddress(shippingAddress: ShippingAddress) {
    this.shippingAddressService
      .createShippingAddress(shippingAddress)
      .subscribe({
        next: (shippingAddress) => {
          this.newShippingAddress = new ShippingAddress();
          this.getShippingAddressByUsername();
          console.log(shippingAddress);
        },
        error: (error) => {
          console.error('Error adding shippingAddress to profile-info', error);
        },
      });
  }


  deleteShippingAddress(shippingAddressId: number) {
    this.shippingAddressService.deleteShippingAddress(shippingAddressId).subscribe({
      next: () => {
        console.log('shippingAddress deleted successfully');
        this.getShippingAddressByUsername();
      },
      error: (error) => {
        console.error('Error deleting shippingAddress', error);
      },
    });
  }




  getCountries() {
    this.countryRestService.getCountries().subscribe({
      next: (data) => {
        this.countries = data.map((country: any) => ({
          name: country.name.common,
          code: country.cca3
        }));
        console.log(this.countries);
      },
      error: (err) => console.error('Failed to get countries', err)
    });
  }





}
