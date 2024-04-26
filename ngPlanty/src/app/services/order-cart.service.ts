import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { environment } from 'src/environments/environment';
import { OrderCart } from '../models/order-cart';
import { Observable, catchError, switchMap, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class OrderCartService {
  private url = environment.baseUrl + 'api/orderCarts';

  constructor(
    private httpClient: HttpClient,
    private authService: AuthService
  ) {}

  getHttpOptions() {
    let options = {
      headers: {
        Authorization: 'Basic ' + this.authService.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
    return options;
  }

  getOrderCart(): Observable<OrderCart> {
    return this.authService.getLoggedInUser().pipe(
      switchMap((user) => {
        if (!user) {
          throw new Error('User not logged in');
        }
        return this.httpClient
          .get<OrderCart>(this.url, this.getHttpOptions())
          .pipe(
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
    );
  }

  removeOrderDetail(
    orderCartId: number,
    orderDetailId: number
  ): Observable<any> {
    return this.authService.getLoggedInUser().pipe(
      switchMap((user) => {
        if (!user) {
          throw new Error('User not logged in');
        }
        const deleteUrl = `${this.url}/${orderCartId}/details/${orderDetailId}`;
        return this.httpClient.delete(deleteUrl, this.getHttpOptions()).pipe(
          catchError((err) => {
            console.error(err);
            return throwError(
              () =>
                new Error(
                  'OrderCartService.removeOrderDetail(): error removing order detail'
                )
            );
          })
        );
      })
    );
  }
  editOrderCart(orderCart: OrderCart, id: number): Observable<OrderCart> {
    return this.authService.getLoggedInUser().pipe(
      switchMap((user) => {
        if (!user) {
          throw new Error('User not logged in');
        }
        const urlWithId = `${this.url}/${id}`;
        return this.httpClient
          .put<OrderCart>(urlWithId, orderCart, this.getHttpOptions())
          .pipe(
            catchError((err: any) => {
              console.log(err);
              return throwError(
                () =>
                  new Error(
                    'OrderCartService.getOrderCarts():error editing orderCart'
                  )
              );
            })
          );
      })
    );
  }

  //CHECKOUT COMPONENT LOGIC

  finalizeOrderCart(orderCartId: number): Observable<OrderCart> {
    return this.authService.getLoggedInUser().pipe(
      switchMap(user => {
        if (!user) {
          throw new Error('User not logged in');
        }
        const finalizeUrl = `${this.url}/${orderCartId}/finalize`;
        return this.httpClient.post<OrderCart>(finalizeUrl, {}, this.getHttpOptions()).pipe(
          catchError(err => {
            console.error('Error finalizing order cart', err);
            return throwError(() => new Error('OrderCartService.finalizeOrderCart(): error finalizing order cart'));
          })
        );
      })
    );
  }
}
