import { User } from './user';

export class PaymentData {
  id: number;
  cardType: string;
  cardNumber: string;
  expirationDate: string;
  enabled: boolean;
  fullName: string;
  user: User;

  constructor(
    id: number = 0,
    cardType: string = '',
    cardNumber: string = '',
    expirationDate: string = '',
    enabled: boolean = false,
    fullName: string = '',
    user: User = new User()
  ) {
    this.id = id;
    this.cardType = cardType;
    this.cardNumber = cardNumber;
    this.expirationDate = expirationDate;
    this.enabled = enabled;
    this.fullName = fullName;
    this.user = user;
  }
}
