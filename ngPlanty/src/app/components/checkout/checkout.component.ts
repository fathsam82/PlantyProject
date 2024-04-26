import { Component, OnInit } from '@angular/core';
import { OrderCartService } from 'src/app/services/order-cart.service';
import { ShippingAddress } from 'src/app/models/shipping-address';
import { OrderCart } from 'src/app/models/order-cart';
import { ShippingAddressService } from 'src/app/services/shipping-address.service';
import { PaymentDataService } from 'src/app/services/payment-data.service';
import { PaymentData } from 'src/app/models/payment-data';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css'],
})
export class CheckoutComponent implements OnInit {
  selectedShippingAddressId: number | undefined;
  selectedPaymentDataId: number | undefined;

  shippingAddressList: ShippingAddress[] | undefined;
  paymentDataList: PaymentData[] | undefined;

  shippingAddress: ShippingAddress | undefined;
  paymentData: PaymentData | undefined;


  orderCart: OrderCart | undefined;
  selectedChoicesForCheckout: OrderCart | undefined;
  editCheckout: OrderCart | null = null;

  isShippingAddressesLoaded: boolean = false;
  isPaymentDataLoaded: boolean = false;

  constructor(
    private orderCartService: OrderCartService,
    private shippingAddressService: ShippingAddressService,
    private paymentDataService: PaymentDataService
  ) {}

  ngOnInit() {
    this.getOrderCart();
    this.getShippingAddressByUsername();
    this.getPaymentDataByUsername();
  }

  getOrderCart() {
    this.orderCartService.getOrderCart().subscribe({
      next: (orderCart) => {
        console.log(orderCart);
        this.orderCart = orderCart;
      },
      error: (badTings) => {
        console.error(
          'OrderCartComponent.getOrderCart: error loading OrderCart'
        );
        console.error(badTings);
        console.error(this.orderCart);
      },
    });
  }

  getShippingAddressByUsername() {
    this.shippingAddressService.getShippingAddressByUsername().subscribe({
      next: (addressList) => {
        this.shippingAddressList = addressList;
        this.isShippingAddressesLoaded = true; // Set flag when data is loaded
      },
      error: (error) => {
        console.error('Error loading shipping addresses', error);
      },
    });
  }

  getPaymentDataByUsername() {
    this.paymentDataService.getPaymentDataByUsername().subscribe({
      next: (paymentList) => {
        this.paymentDataList = paymentList;
        this.isPaymentDataLoaded = true; // Set flag when data is loaded
      },
      error: (error) => {
        console.error('Error loading payment data', error);
      },
    });
  }




}



