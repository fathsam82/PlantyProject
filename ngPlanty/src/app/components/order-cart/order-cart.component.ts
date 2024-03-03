import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { OrderCart } from 'src/app/models/order-cart';
import { OrderDetail } from 'src/app/models/order-detail';
import { OrderCartService } from 'src/app/services/order-cart.service';

@Component({
  selector: 'app-order-cart',
  templateUrl: './order-cart.component.html',
  styleUrl: './order-cart.component.css',
})
export class OrderCartComponent implements OnInit {
  orderDetails: OrderDetail[] | undefined;
  orderCart: OrderCart | undefined;
  selectedOrderCart: OrderCart | null = null;
  editOrderCart: OrderCart | null = null;

  constructor(
    private orderCartService: OrderCartService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit() {
    this.getOrderCart();
  }


  getOrderCart(){
    this.orderCartService.getOrderCart().subscribe({
      next: (orderCart) => {
        console.log(orderCart);
        this.orderCart = orderCart;
      },
      error: (badTings) => {
        console.error('OrderCartComponent.getOrderCart: error loading OrderCart');
        console.error(badTings);
        console.error(this.orderCart);
      }
    })

  }

  updateOrderCart(orderCart: OrderCart, id: number, setSelected: boolean = true) {
    this.orderCartService.editOrderCart(orderCart, id).subscribe({
      next: (updatedOrderCart) => {
        if (setSelected) {
          this.selectedOrderCart = updatedOrderCart;
        }
        this.editOrderCart = null;
        console.log('OrderCart updated successfully!', updatedOrderCart);
        this.getOrderCart();
      },
      error: (whoops) => {
        console.error('OrderCartComponent.updateOrderCart: Error on update', whoops);
      }
    });
  }

  initiateEditOrderCart() {
    this.editOrderCart = Object.assign({}, this.selectedOrderCart);
  }

  clearSelectedOrderCart() {
    this.selectedOrderCart = null;
  }
}

