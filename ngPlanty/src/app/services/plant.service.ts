import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { Plant } from '../models/plant';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class PlantService {

  private baseUrl = 'http://localhost:8085/';
  private url = environment.baseUrl + 'api/plants'

  constructor(private http: HttpClient) {}

  index(): Observable<Plant[]> {
    return this.http.get<Plant[]>(this.url).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('PlantService.index(): error retrieving plant')
        )
      })
    )
  }
}
