import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, switchMap } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root',
})
export class CountryRestService {
  private url = 'https://restcountries.com/v3.1/all?fields=name,cca3';

  constructor(private httpClient: HttpClient,
  private authService: AuthService)
  {}



  getHttpOptions() {
    let options = {
      headers: {
        Authorization: 'Basic ' + this.authService.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
    return options;
  }


  getCountries(): Observable<any> {
    return this.httpClient.get(this.url);
  }

}
