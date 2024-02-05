import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { Plant } from '../models/plant';
import { environment } from 'src/environments/environment';
// import { DatePipe } from '@angular/common';
// import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root',
})
export class PlantService {

  // private baseUrl = 'http://localhost:8085/';
  private url = environment.baseUrl + "api/plants";

  constructor(
    private http: HttpClient,
    // private datePipe: DatePipe,
    // private auth: AuthService
    ) {}


//     getHttpOptions(){
//       let options = {
//       headers: {
//         Authorization: 'Basic ' + this.authService.getCredentials(),
//       'X-Requested-With': 'XMLHttpRequest',
//     },
//   };
//   return options;
// }

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

  getPlant(id: number): Observable<Plant> {
    return this.http.get<Plant>(this.url + '/' + id).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(() => new Error('PlantService.getPlant(): error retrieving plant by id ${id}'));

      })
    );
  }


  getPlantByName(name: string): Observable<Plant> {
    return this.http.get<Plant>(this.url + '/name/' + name).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () =>
            new Error(
              'PlantService.getPlantsByName(): error retrieving Resources for user: ' + name + err
            )
        );
      })
    );
  }
}
