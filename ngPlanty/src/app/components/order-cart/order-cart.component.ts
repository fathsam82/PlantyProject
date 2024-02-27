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

  ngOnInit() {
    this.getOrderCart();
  }


  getOrderCart(){
    this.orderCartService.getOrderCart().subscribe({
      next: (orderCart) => {
        console.log(orderCart)
        this.orderCart = orderCart;
      },
      error: (badTings) => {
        console.error('OrderCartComponent.getOrderCart: error loading OrderCart');
        console.error(badTings);
        console.error(this.orderCart);
      }
    })

  }
}
