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
  shippingAddressList: ShippingAddress[] | undefined;
  selectedShippingAddress: ShippingAddress | null = null;
  orderCart: OrderCart | undefined;
  paymentDataList: PaymentData[] | undefined;
  selectedPaymentData: PaymentData | null = null;
  selectedPaymentDataId: number | undefined;


  constructor(
    private orderCartService: OrderCartService,
    private shippingAddressService: ShippingAddressService,
    private paymentDataService: PaymentDataService
  ) {}

  ngOnInit() {
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
      next: (addresses) => {
        this.shippingAddressList = addresses;
      },
      error: (error) => {
        console.error(
          'ProfileInfoComponent.getShippingAddresses(): error loading shipping addresses',
          error
        );
      },
    });
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

  // updateCheckoutOrderCart(
  //   orderCart: OrderCart,
  //   id: number,
  //   setSelectedShippingAddress: boolean = true
  // ) {
  //   this.orderCartService.checkoutOrderCart(orderCart, id).subscribe({
  //     next: (updatedShippingAddress) => {
  //       if (setSelectedShippingAddress) {
  //         this.selectedShippingAddress = updatedShippingAddress.shippingAddress;
  //       }
  //       console.log(
  //         'ShippingAddress updated successfully!',
  //         updatedShippingAddress
  //       );
  //     },
  //     error: (whoopsydaisy) => {
  //       console.error(
  //         'CheckoutComponent.updateCheckoutOrderCart: Error on update',
  //         whoopsydaisy
  //       );
  //     },
  //   });
  // }

  // performCheckout(orderCart: OrderCart, orderCartId: number) {
  //   this.orderCartService.checkoutOrderCart(orderCart, orderCartId).subscribe({
  //     next:() => {
  //       console.log('OrderCart successfully cleared');
  //       this.getOrderCart();
  //     },
  //     error: (error) => {
  //       console.error('Error proccessing checkout and clearing OrderCart', error);
  //     },
  //   });
  // }
}
