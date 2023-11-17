import { PlantCategory } from './plant-category';

export class Plant {
  id: number;
  name: string;
  description: string;
  price: number;
  stockQuantity: number;
  plantImageUrl: string;
  size: string;
  isDiscounted: boolean;
  plantCat: PlantCategory;
  enabled: boolean;

  constructor(
    id: number = 0,
    name: string = '',
    description: string = '',
    price = 0,
    stockQuantity: number = 0,
    plantImageUrl: string = '',
    size: string = '',
    isDiscounted: boolean = false,
    plantCat: PlantCategory = new PlantCategory(),
    enabled: boolean
  ) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.price = price;
    this.stockQuantity = stockQuantity;
    this.plantImageUrl = plantImageUrl;
    this.size = size;
    this.isDiscounted = isDiscounted;
    this.plantCat = plantCat;
    this.enabled = enabled;
  }
}
