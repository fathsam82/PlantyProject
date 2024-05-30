import { PlantOriginService } from './../../services/plant-origin.service';
import { Component, OnInit } from '@angular/core';
import { PlantService } from 'src/app/services/plant.service';
import { Plant } from 'src/app/models/plant';
import { FormsModule } from '@angular/forms';
import { OrderDetailService } from 'src/app/services/order-detail.service';
import { ActivatedRoute, Router } from '@angular/router';
import { PlantOrigin } from 'src/app/models/plant-origin';
import { ConfirmationDialogComponent } from 'src/app/confirmation-dialog/confirmation-dialog.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-plant-detail',
  templateUrl: './plant-detail.component.html',
  styleUrls: ['./plant-detail.component.css'],
})
export class PlantDetailComponent implements OnInit {
  plant: Plant | undefined;
  quantity: number = 1;
  giftWrap: boolean = false;
  origin: PlantOrigin | undefined;
  readonly maxQuantity: number = 20000000;

  constructor(
    private plantService: PlantService,
    private activatedRoute: ActivatedRoute,
    private orderDetailService: OrderDetailService,
    private router: Router,
    private plantOriginService: PlantOriginService,
    private dialog: MatDialog
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
          next: (plant) => {
            this.plant = plant;
            this.getOriginDetails(id);
          },
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
    if (!this.plant || this.quantity <= 0 || this.quantity > this.maxQuantity) {
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
          this.dialog.open(ConfirmationDialogComponent, {
            data: {
              title: 'Add to Order Failed',
              message: 'You must be logged in to add plants to your order.',
              linkText: 'Go to Login',
              linkRoute: '/login',
            },
          });
        },
      });
  }

  getOriginDetails(plantId: number) {
    this.plantOriginService.getPlantOrigin(plantId).subscribe({
      next: (origin) => (this.origin = origin),
      error: (err) => console.error('Failed to load origin', err),
    });
  }
}
