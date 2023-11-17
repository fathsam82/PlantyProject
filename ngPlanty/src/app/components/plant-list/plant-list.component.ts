import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PlantService } from 'src/app/services/plant.service';
import { Plant } from 'src/app/models/plant';

@Component({
  selector: 'app-plant-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './plant-list.component.html',
  styleUrl: './plant-list.component.css'
})
export class PlantListComponent implements OnInit {

  title = "ngPlant";

  plants: Plant[] = [];

  selected: Plant | null = null;



  constructor(private plantService: PlantService){

  }

  ngOnInit() {
    this.reload();

  }

  reload(){
    this.plantService.index().subscribe({
      next: (plantList) => {
        this.plants = plantList;
      },
      error: (somethingBad) => {
        console.error('PlantListComponent.reload: error loading plants');
        console.error(somethingBad);
      }
    });



  }

}
