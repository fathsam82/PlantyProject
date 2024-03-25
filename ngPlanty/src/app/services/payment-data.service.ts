import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth.service';
import { PaymentData } from '../models/payment-data';
import { Observable, catchError, switchMap, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PaymentDataService {
  private url = environment.baseUrl + 'api/paymentData';

  constructor(
    private httpClient: HttpClient,
    private authService: AuthService
  ) { }

  getHttpOptions() {
    let options = {
      headers: {
        Authorization: 'Basic ' + this.authService.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
    return options;
  }

  getPaymentDataByUsername(username: string): Observable<PaymentData> {
    return this.authService.getLoggedInUser().pipe(
      switchMap((user) => {
        if(!user) {
          throw new Error('User not logged in');
        }
        const urlWithUsername = `${this.url}/${username}`;
        return this.httpClient.get<PaymentData>(urlWithUsername, this.getHttpOptions()).pipe(
          catchError((err: any) => {
            console.log(err);
            return throwError(
              () =>
              new Error('PaymentDataService.getPaymentDataByUsername(): error retrieving paymentData')
            );
          })
        );
      })
    )
  }

}
