import { OrderDetail } from './order-detail';
import { PaymentData } from './payment-data';
import { ShippingAddress } from './shipping-address';
import { User } from './user';

export class OrderCart {
  id: number;
  totalPrice: number;
  datePlaced: string;
  notes: string;
  estimatedDeliveryDate: string;
  trackingNumber: number;
  paymentMethod: string;
  enabled: boolean;
  user: User;
  paymentData: PaymentData;
  shippingAddress: ShippingAddress;
  orderDetails: OrderDetail[];

  constructor(
    id: number = 0,
    totalPrice: number = 0,
    datePlaced: string = '',
    notes: string = '',
    estimatedDeliveryDate: string = '',
    trackingNumber: number = 0,
    paymentMethod: string = '',
    enabled: boolean = false,
    user: User = new User(),
    paymentData: PaymentData = new PaymentData,
    shippingAddress: ShippingAddress = new ShippingAddress,
    orderDetails: OrderDetail[] = []
  ) {
    this.id = id;
    this.totalPrice = totalPrice;
    this.datePlaced = datePlaced;
    this.notes = notes;
    this.estimatedDeliveryDate = estimatedDeliveryDate;
    this.trackingNumber = trackingNumber;
    this.paymentMethod = paymentMethod;
    this.enabled = enabled;
    this.user = user;
    this.paymentData = paymentData;
    this.shippingAddress = shippingAddress;
    this.orderDetails = orderDetails;
  }
}
