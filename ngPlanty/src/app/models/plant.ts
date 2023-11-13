import { PlantCategory } from "./plant-category"

export class Plant {
  id: number;
  name: string;
  description: string;
  price: number;
  stockQuantity: number;
  plantImageUrl: string;
  size: string;
  isDiscounted: boolean;
  PlantCat: PlantCategory;
  enabled: boolean;

  constructor(id: number = 0,
     name: string = '',
      description = '', price = 0,
       stockQuantity = 0,
        plantImageUrl = '',
         size = '',
          isDiscounted = false,
           plantCat: PlantCategory = new PlantCategory(),
            enabled: boolean) {

              this.id = id;
              this.name = name;
              this.description = description;
              this.price = price;
              this.stockQuantity = stockQuantity;
              this.plantImageUrl = plantImageUrl;
              this.enabled = enabled;
              this.size = size;
              this.isDiscounted = isDiscounted;
              this.PlantCat = plantCat;
              this.enabled = enabled;
            }



}
