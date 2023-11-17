import { Plant } from './plant';

export class PlantOrigin {
  id: number;
  name: string;
  latitude: number;
  longitude: number;
  plant: Plant;

  constructor(
    id: number = 0,
    name: string = '',
    latitude: number = 0,
    longitude: number = 0,
    plant: Plant = new Plant()
  ) {
    this.id = id;
    this.name = name;
    this.latitude = latitude;
    this.longitude = longitude;
    this.plant = plant;
  }
}
