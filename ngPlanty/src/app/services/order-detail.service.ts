import { AuthService } from './auth.service';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { OrderDetail } from '../models/order-detail';
import { Observable, catchError, switchMap, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class OrderDetailService {
  private url = environment.baseUrl + 'api/orderDetails';

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

  getOrderDetail(id: number): Observable<OrderDetail> {
    return this.authService.getLoggedInUser().pipe(
      switchMap((user) => {
        if (!user) {
          throw new Error('User not logged in');
        }
        return this.httpClient.get<OrderDetail>(this.url + '/' + id).pipe(
          catchError((err: any) => {
            console.log(err);
            return throwError(
              () =>
                new Error(
                  'OrderDetailService.getOrderDetail(): error retrieving plant by id ${id}'
                )
            );
          })
        );
      }),
      catchError((err: any) => {
        console.log(err);
        return throwError(() =>
          Error(
            'OrderDetailService.getOrderDetail(): error checking user login'
          )
        );
      })
    );
  }

  createOrderDetail(
    plantId: number,
    quantity: number,
    giftWrap: boolean
  ): Observable<OrderDetail> {
    return this.authService.getLoggedInUser().pipe(
      switchMap((user) => {
        if (!user) {
          throw new Error('User not logged in');
        }
        const urlWithParams = `${this.url}/addPlant/${plantId}/${quantity}/${giftWrap}`;
        return this.httpClient.post<OrderDetail>(
          urlWithParams,
          null,
          this.getHttpOptions()
        );
      }),
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error(
              'OrderDetailService.create(): error creating orderDetail: ' + err
            )
        );
      })
    );
  }
}
