import { User } from './user';

export class ShippingAddress {

  id: number;
  streetAddress: string;
  city: string;
  state: string;
  zipcode: string;
  country: string;
  enabled: boolean;
  user: User;

  constructor(
    id: number = 0,
    streetAddress: string = '',
    city: string = '',
    state: string = '',
    zipcode: string = '',
    country: string = '',
    enabled: boolean = false,
    user: User = new User()



  ){
    this.id = id;
    this.streetAddress = streetAddress;
    this.city = city;
    this.state = state;
    this.zipcode = zipcode;
    this.country = country;
    this.enabled = enabled;
    this.user = user;

  }




}
