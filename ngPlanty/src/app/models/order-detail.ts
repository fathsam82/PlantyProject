import { Plant } from './plant';
import { OrderCart } from "./order-cart";

export class OrderDetail {

  id: number;
  quantityOrdered: number;
  unitPrice: number;
  subtotalPrice: number;
  giftWrap: boolean;
  orderCart: OrderCart;
  plant: Plant;

  constructor(
    id: number = 0,
    quantityOrdered: number = 0,
    unitPrice: number = 0,
    subtotalPrice: number = 0,
    giftWrap: boolean = false,
    orderCart: OrderCart = new OrderCart(),
    plant: Plant = new Plant()
  ){
  this.id = id;
  this.quantityOrdered = quantityOrdered;
  this.unitPrice = unitPrice;
  this.subtotalPrice = subtotalPrice;
  this.giftWrap = giftWrap;
  this.orderCart = orderCart;
  this.plant = plant;

  }
}
