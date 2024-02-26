import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { OrderDetailService } from 'src/app/services/order-detail.service';
import { OrderDetail } from 'src/app/models/order-detail';
import { Plant } from 'src/app/models/plant';
import { PlantService } from 'src/app/services/plant.service';

@Component({
  selector: 'app-order-detail',
  templateUrl: './order-detail.component.html',
  styleUrl: './order-detail.component.css',
})
export class OrderDetailComponent implements OnInit {
  orderDetail: OrderDetail | undefined;
  plant: Plant | undefined;
  itemAddedToCart: boolean = false;

  constructor(
    private orderDetailService: OrderDetailService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit() {
    this.getOrderDetail();
  }

  getOrderDetail() {
    const orderDetailId = this.activatedRoute.snapshot.paramMap.get('id');
    if (orderDetailId !== null) {
      const id = Number(orderDetailId);
      if (!isNaN(id)) {
        this.orderDetailService.getOrderDetail(id).subscribe({
          next: (orderDetail) => {
            this.orderDetail = orderDetail;
            if (orderDetail.plant !== undefined && orderDetail.plant !== null) {
              this.plant = orderDetail.plant;
            } else {
              console.error('Plant data is missing in orderDetail');
            }
          },
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
