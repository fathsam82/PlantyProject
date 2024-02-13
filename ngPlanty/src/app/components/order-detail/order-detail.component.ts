import { ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { OrderDetailService } from 'src/app/services/order-detail.service';
import { OrderDetail } from 'src/app/models/order-detail';

@Component({
  selector: 'app-order-detail',
  templateUrl: './order-detail.component.html',
  styleUrl: './order-detail.component.css',
})
export class OrderDetailComponent {
  orderDetail: OrderDetail | undefined;

  constructor(
    private orderDetailService: OrderDetailService,
    private activatedRoute: ActivatedRoute
  ) {}

  getOrderDetail() {
    const orderDetailId = this.activatedRoute.snapshot.paramMap.get('id');
    if (orderDetailId !== null) {
      const id = Number(orderDetailId);
      if (!isNaN(id)) {
        this.orderDetailService.getOrderDetail(id).subscribe({
          next: (orderDetail) => (this.orderDetail = orderDetail),
          error: (err) => console.error(err),
        });
      } else {
        console.error('Invalid orderDetail ID:', orderDetailId);
      }
    } else {
      console.error('OrderDetail ID is missing the route parameters');
    }
  }
}
