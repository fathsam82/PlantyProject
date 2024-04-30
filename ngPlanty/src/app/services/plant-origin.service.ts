import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { PlantOrigin } from '../models/plant-origin';


@Injectable({
  providedIn: 'root'
})
export class PlantOriginService {

  private url = environment.baseUrl + 'api/plantOrigin'

  constructor(private http: HttpClient) { }

  getPlantOrigin(id: number): Observable<PlantOrigin> {
    return this.http.get<PlantOrigin>(this.url + '/' + id).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
          new Error(
            'PlantOriginService.getPlantOrigin(): error retrieving plantOrigin by id ${id}'
          )
        )
      })
    )

  }
}
