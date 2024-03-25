import { User } from './user';

export class PaymentData {
  id: number;
  cardType: string;
  cardNumber: string;
  expirationDate: string;
  enabled: boolean;
  user: User;

  constructor(
    id: number = 0,
    cardType: string = '',
    cardNumber: string = '',
    expirationDate: string = '',
    enabled: boolean = false,
    user: User = new User()
  ) {
    this.id = id;
    this.cardType = cardType;
    this.cardNumber = cardNumber;
    this.expirationDate = expirationDate;
    this.enabled = enabled;
    this.user = user;
  }
}
