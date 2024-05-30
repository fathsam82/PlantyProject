import { Component, OnInit } from '@angular/core';
import { PlantService } from 'src/app/services/plant.service';
import { Plant } from 'src/app/models/plant';
import { Router } from '@angular/router';

@Component({
  selector: 'app-plant-list',
  templateUrl: './plant-list.component.html',
  styleUrl: './plant-list.component.css',
})
export class PlantListComponent implements OnInit {
  title = 'ngPlant';

  plants: Plant[] | undefined;

  selected: Plant | null = null;

  searchPlantQuery: string = '';

  errorMessage: string = '';


  constructor(private plantService: PlantService, private router: Router) {}

  ngOnInit() {
    this.reload();
    // this.getPlantByName();
  }

  reload() {
    this.errorMessage = '';
    this.plantService.index().subscribe({
      next: (plants) => {
        this.plants = plants;
      },
      error: (somethingBad) => {
        console.error('PlantListComponent.reload: error loading plants');
        console.error(somethingBad);
        console.log(this.plants);
      },
    });
  }

  getPlantByName() {
    if (this.searchPlantQuery.trim()) {
      this.plantService.getPlantByName(this.searchPlantQuery).subscribe({
        next: (plant) => {
          this.plants = [plant];
          this.errorMessage = '';
        },
        error: (badTings) => {
          console.error('PlantListComponent.getPlantByName: error loading plant');
          console.error(badTings);
          // this.plants = [];
          this.errorMessage = 'No plants found with the name "' + this.searchPlantQuery + '"';
        }
      });
    } else {
      this.reload();
    }
  }
}
