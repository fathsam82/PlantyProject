

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PlantService } from 'src/app/services/plant.service';
import { Plant } from 'src/app/models/plant';
import { FormsModule } from '@angular/forms';
import { OrderDetailService } from 'src/app/services/order-detail.service';



@Component({
  selector: 'app-plant-detail',
  templateUrl: './plant-detail.component.html',
  styleUrls: ['./plant-detail.component.css']
})
export class PlantDetailComponent implements OnInit {
  plant: Plant | undefined;
  quantity: number = 1;
  giftWrap: boolean = false;

  constructor(
    private plantService: PlantService,
    private route: ActivatedRoute,
    private orderDetailService: OrderDetailService
  ) {}

  ngOnInit() {
    this.getPlantDetails();
  }

  getPlantDetails() {
    const plantId = this.route.snapshot.paramMap.get('id');
    if (plantId !== null) {
      const id = Number(plantId);
      if (!isNaN(id)) {
        this.plantService.getPlant(id).subscribe({
          next: (plant) => this.plant = plant,
          error: (err) => console.error(err)
        });
      } else {
        console.error('Invalid plant ID:', plantId);
      }
    } else {
      console.error('Plant ID is missing in the route parameters.');
    }
  }



}

