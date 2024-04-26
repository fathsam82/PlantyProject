import { Component, OnInit } from '@angular/core';
import { OrderCartService } from 'src/app/services/order-cart.service';
import { ShippingAddress } from 'src/app/models/shipping-address';
import { OrderCart } from 'src/app/models/order-cart';
import { ShippingAddressService } from 'src/app/services/shipping-address.service';
import { PaymentDataService } from 'src/app/services/payment-data.service';
import { PaymentData } from 'src/app/models/payment-data';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmationDialogComponent } from 'src/app/confirmation-dialog/confirmation-dialog.component';

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

  displayFinalizationDetails: boolean = false;

  isShippingAddressesLoaded: boolean = false;
  isPaymentDataLoaded: boolean = false;

  constructor(
    private orderCartService: OrderCartService,
    private shippingAddressService: ShippingAddressService,
    private paymentDataService: PaymentDataService,
    private dialog: MatDialog,
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

  finalizeOrder(): void {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent);

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        if (this.orderCart && this.orderCart.id) {
          this.orderCartService.finalizeOrderCart(this.orderCart.id).subscribe({
            next: (updatedOrderCart) => {
              this.orderCart = updatedOrderCart;
              console.log('Order finalized successfully', updatedOrderCart);
              this.displayFinalizationDetails = true;
            },
            error: (error) => {
              console.error('Failed to finalize order', error);
            }
          });
        } else {
          console.error('Order cart data is incomplete.');
        }
      }
    });
  }



  onSubmit(): void {
    if (this.selectedShippingAddressId && this.selectedPaymentDataId) {
      const dialogRef = this.dialog.open(ConfirmationDialogComponent);

      dialogRef.afterClosed().subscribe(result => {
        if (result) {
          this.finalizeOrder();
        } else {
          console.log('Order finalization canceled');
        }
      });
    } else {
      console.error('Shipping address or payment data is not selected.');
    }
  }


  onShippingAddressChange(newAddressId: number): void {
    this.selectedShippingAddressId = newAddressId;
    console.log('Selected shipping address ID:', newAddressId);
  }

  onPaymentDataChange(newPaymentDataId: number): void {
    this.selectedPaymentDataId = newPaymentDataId;
    console.log('Selected payment data ID:', newPaymentDataId);
  }


}



