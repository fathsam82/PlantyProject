import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { AuthService } from './auth.service';
import { Comment } from '../models/comment';
import { catchError, Observable, switchMap, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CommentService {
  private url = environment.baseUrl + 'api/comments';

  constructor(
    private authService: AuthService,
    private httpClient: HttpClient
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

  getCommentsByPost(): Observable<Comment[]> {
    return this.authService.getLoggedInUser().pipe(
      switchMap((user) => {
        if (!user) {
          throw new Error('User not logged in');
        }
        return this.httpClient
          .get<Comment[]>(this.url, this.getHttpOptions())
          .pipe(
            catchError((err: any) => {
              console.log(err);
              return throwError(
                () =>
                  new Error(
                    'CommentService.getCommentByPost(): error retrieving comments'
                  )
              );
            })
          );
      })
    );
  }
}
