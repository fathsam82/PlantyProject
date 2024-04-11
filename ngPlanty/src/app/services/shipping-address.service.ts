import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, switchMap, throwError } from 'rxjs';
import { AuthService } from 'src/app/services/auth.service';
import { environment } from 'src/environments/environment';
import { ShippingAddress } from '../models/shipping-address';

@Injectable({
  providedIn: 'root'
})
export class ShippingAddressService {
  private url = environment.baseUrl + 'api/shippingAddress';

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


  getShippingAddressByUsername(): Observable<ShippingAddress[]> {

    return this.authService.getLoggedInUser().pipe(
      switchMap((user) => {
        if (!user) {
          throw new Error('User not logged in');
        }
        return this.httpClient
          .get<ShippingAddress[]>(this.url, this.getHttpOptions())
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

}
