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

  constructor(
    private orderCartService: OrderCartService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit() {}

  reload() {

  }
  loadOrderCart(){
    const cartId = this.activatedRoute.snapshot.paramMap.get('id');
    if (cartId){
      const id = Number(cartId);
      this.orderCartService.getOrderCarts(id).subscribe({
        next: (orderCart) => {
          this.orderCart = orderCart;
        },
        error: (err) => console.error(err),

      });
    } else {
      console.error('Cart ID is missing in the route parameters');
    }
  }
}
