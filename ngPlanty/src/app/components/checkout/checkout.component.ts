import { Component, OnInit } from '@angular/core';
import { OrderCartService } from 'src/app/services/order-cart.service';
import { ShippingAddress } from 'src/app/models/shipping-address';
import { OrderCart } from 'src/app/models/order-cart';
import { ShippingAddressService } from 'src/app/services/shipping-address.service';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css'],
})
export class CheckoutComponent implements OnInit {
  selectedShippingAddressId: number | undefined;
  shippingAddresses: ShippingAddress[] | undefined;
  selectedShippingAddress: ShippingAddress | null = null;
  orderCart: OrderCart | undefined;
  prepareOrderCartData: any;

  constructor(private orderCartService: OrderCartService,
    private shippingAddressService: ShippingAddressService) {}


  ngOnInit() {
    this.loadShippingAddresses();
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



  loadShippingAddresses() {
    this.shippingAddressService.getShippingAddressByUsername().subscribe(
      addresses => {
        this.shippingAddresses = addresses;
      },
      error => console.error('Failed to load shipping addresses', error)
    );
  }


  updateCheckoutOrderCart(
    orderCart: OrderCart,
    id: number,
    setSelectedShippingAddress: boolean = true
  ) {
    this.orderCartService.checkoutOrderCart(orderCart, id).subscribe({
      next: (updatedShippingAddress) => {
        if (setSelectedShippingAddress) {
          this.selectedShippingAddress = updatedShippingAddress.shippingAddress;
        }
        console.log(
          'ShippingAddress updated successfully!',
          updatedShippingAddress
        );
      },
      error: (whoopsydaisy) => {
        console.error(
          'CheckoutComponent.updateCheckoutOrderCart: Error on update',
          whoopsydaisy
        );
      },
    });
  }


  performCheckout() {
    // Assuming prepareOrderCartData correctly sets the shipping address:
    const orderCart = this.prepareOrderCartData();
    this.orderCartService.checkoutOrderCart(orderCart, orderCart.id).subscribe(
      success => {
        console.log('Checkout successful', success);
        // Optionally update local shipping address display
        this.selectedShippingAddress = orderCart.shippingAddress;
      },
      error => console.error('Checkout failed', error)
    );
  }

}
