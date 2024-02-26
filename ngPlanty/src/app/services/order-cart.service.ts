import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { environment } from 'src/environments/environment';
import { OrderCart } from '../models/order-cart';
import { Observable, catchError, switchMap, throwError } from 'rxjs';



@Injectable({
  providedIn: 'root'
})
export class OrderCartService {
  private url = environment.baseUrl + 'api/orderCarts';

  constructor(private httpClient: HttpClient,
    private authService: AuthService) { }

    getHttpOptions() {
      let options = {
        headers: {
          Authorization: 'Basic' + this.authService.getCredentials(),
          'X-Requested-With': 'XMLHttpRequest',
        },
      };
      return options;
    }



    getOrderCarts(id: number): Observable<OrderCart> {
      return this.authService.getLoggedInUser().pipe(
        switchMap((user) => {
          if(!user) {
            throw new Error('User not logged in');
          }
          return this.httpClient.get<OrderCart>(this.url).pipe(
            catchError((err: any) => {
              console.log(err);
              return throwError(
                () =>
                new Error(
                  'OrderCartService.getOrderCarts(): error retrieving orderCart'
                )
              );
            })
          );
        })
      )
    }
}
