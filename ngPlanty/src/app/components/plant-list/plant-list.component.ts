import { PlantCategory } from './../../models/plant-category';
import { Component, OnInit } from '@angular/core';
import { PlantService } from 'src/app/services/plant.service';
import { Plant } from 'src/app/models/plant';
import { Router } from '@angular/router';
import { BehaviorSubject } from 'rxjs';

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

  plantCatId: number | undefined;

  plantCategories: { id: number; name: string }[] = [
    { id: 1, name: 'Evergreen Perennials' },
    { id: 2, name: 'Tropical Trees' },
    { id: 3, name: 'Succulents' },
  ];

  // selectedCategory$ = new BehaviorSubject<number | null>(null);

  constructor(private plantService: PlantService, private router: Router) {}

  ngOnInit() {
    this.reload();
    // this.getPlantByName();

    // this.selectedCategory$.subscribe((categoryId)=>{
    //   if(categoryId){
    //     this.getPlantByCat();
    //   } else {
    //     this.reload();
    //   }
    // });
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
          console.error(
            'PlantListComponent.getPlantByName: error loading plant'
          );
          console.error(badTings);
          // this.plants = [];
          this.errorMessage =
            'No plants found with the name "' + this.searchPlantQuery + '"';
        },
      });
    } else {
      this.reload();
    }
  }

  filterPlantsByCategory(categoryId: number){
    this.plantCatId = categoryId;
    this.getPlantByCat();
  }

  getPlantByCat() {
    if (this.plantCatId) {
      this.plantService.getPlantByCat(this.plantCatId).subscribe({
        next: (plants) => {
          this.plants = plants;
          this.errorMessage = '';
        },
        error: (error) => {
          console.error(
            'PlantListComponent.selectCategory: error loading plants by category'
          );
          console.error(error);
          this.errorMessage =
            'An error occurred while fetching for plants based on this category';
        },
      });
    } else {
      this.reload();
    }
  }
}
