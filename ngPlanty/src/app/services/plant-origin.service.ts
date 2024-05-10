import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, switchMap, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { PlantOrigin } from '../models/plant-origin';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class PlantOriginService {

  private url = environment.baseUrl + 'api/plantOrigin';

  constructor(private http: HttpClient, private authService: AuthService) {}

  getHttpOptions() {
    let options = {
      headers: {
        Authorization: 'Basic ' + this.authService.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
    return options;
  }

  getPlantOrigin(id: number): Observable<PlantOrigin> {
    return this.authService.getLoggedInUser().pipe(
      switchMap((user) => {
        if (!user) {
          throw new Error('User not logged in.');
        }

        return this.http.get<PlantOrigin>(`${this.url}/${id}`, this.getHttpOptions()).pipe(
          catchError((err: any) => {
            console.error(`PlantOriginService.getPlantOrigin(): error retrieving plantOrigin by id ${id}`, err);
            return throwError(() => new Error(`PlantOriginService.getPlantOrigin(): error retrieving plantOrigin by id ${id}`));
          })
        );
      })
    );
  }
}
