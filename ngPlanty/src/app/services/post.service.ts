import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth.service';
import { Post } from '../models/post';
import { catchError, Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class PostService {
  private url = environment.baseUrl + 'api/posts';

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

  index(): Observable<Post[]> {
    return this.httpClient.get<Post[]>(this.url, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('PostService.create(): error creating posts: ' + err)
        );
      })
    );
  }

  // createPost(post: Post): Observable<Post>{
  //   this.authService.getLoggedInUser().subscribe({
  //     next: (user) => {

  //     },
  //     error: (fail) => {
  //       console.error('ngOnInit(): Error getting user');
  //       console.error(fail);
  //     }
  //   });
  //   return this.httpClient.post<Post>(this.url, post, this.getHttpOptions()).pipe(
  //     catchError((err: any) => {
  //       console.log(err);
  //       return throwError(
  //         () => new Error('PostService.createPost(): error creating posts: ' + err)
  //       );
  //     })
  //   );
  // }


}
