import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth.service';
import { PaymentData } from '../models/payment-data';
import { Observable, catchError, switchMap, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class PaymentDataService {
  private url = environment.baseUrl + 'api/paymentData';

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

  getPaymentDataByUsername(): Observable<PaymentData[]> {
    return this.authService.getLoggedInUser().pipe(
      switchMap((user) => {
        if (!user) {
          throw new Error('User not logged in');
        }
        return this.httpClient
          .get<PaymentData[]>(this.url, this.getHttpOptions())
          .pipe(
            catchError((err: any) => {
              console.log(err);
              return throwError(
                () =>
                  new Error(
                    'PaymentDataService.getPaymentDataByUsername(): error retrieving paymentData'
                  )
              );
            })
          );
      })
    );
  }

  getPaymentDataById(paymentDataId: number): Observable<PaymentData> {
    return this.authService.getLoggedInUser().pipe(
      switchMap((user) => {
        if(!user) {
          throw new Error('User not logged in');
        }
        return this.httpClient.get<PaymentData>(this.url + '/' + paymentDataId, this.getHttpOptions())
        .pipe(
          catchError((err: any) => {
            console.log(err);
            return throwError(
              () =>
              new Error(
                'PaymentDataService.getPaymentDataById(): error retrieving paymentData'
              )
            )
          })
        )
      })
    )
  }

  createPaymentData(paymentData: PaymentData): Observable<PaymentData> {
    return this.authService.getLoggedInUser().pipe(
      switchMap((user) => {
        if (!user) {
          throw new Error('User not logged in');
        }
        return this.httpClient.post<PaymentData>(this.url, paymentData, this.getHttpOptions())
          .pipe(
            catchError((err: any) => {
              console.log(err);
              return throwError(
                () =>
                  new Error(
                    'PaymentDataService.createPaymentData(): error creating paymentData'
                  )
              );
            })
          );
      })
    );
  }
}
