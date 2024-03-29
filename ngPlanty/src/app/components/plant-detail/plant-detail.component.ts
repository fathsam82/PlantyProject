import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PlantService } from 'src/app/services/plant.service';
import { Plant } from 'src/app/models/plant';
import { FormsModule } from '@angular/forms';
import { OrderDetailService } from 'src/app/services/order-detail.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-plant-detail',
  templateUrl: './plant-detail.component.html',
  styleUrls: ['./plant-detail.component.css'],
})
export class PlantDetailComponent implements OnInit {
  plant: Plant | undefined;
  quantity: number = 1;
  giftWrap: boolean = false;

  constructor(
    private plantService: PlantService,
    private activatedRoute: ActivatedRoute,
    private orderDetailService: OrderDetailService,
    private router: Router
  ) {}

  ngOnInit() {
    this.getPlantDetails();
  }

  getPlantDetails() {
    const plantId = this.activatedRoute.snapshot.paramMap.get('id');
    if (plantId !== null) {
      const id = Number(plantId);
      if (!isNaN(id)) {
        this.plantService.getPlant(id).subscribe({
          next: (plant) => (this.plant = plant),
          error: (err) => console.error(err),
        });
      } else {
        console.error('Invalid plant ID:', plantId);
      }
    } else {
      console.error('Plant ID is missing in the route parameters.');
    }
  }

  addToOrderDetail() {
    if (!this.plant || this.quantity <= 0) {
      console.error('Invalid plant or quantity');
      return;
    }

    this.orderDetailService
      .createOrderDetail(this.plant.id, this.quantity, this.giftWrap)
      .subscribe({
        next: (orderDetail) => {
          console.log('OrderDetail added successfully', orderDetail);
          this.router.navigate(['/order-detail', orderDetail.id]);
        },
        error: (error) => {
          console.error('Error adding plant to order', error);
        },
      });
  }
}
