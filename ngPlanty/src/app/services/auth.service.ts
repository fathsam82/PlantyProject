
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { tap, catchError, throwError, Observable, of } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../models/user';
import { Buffer } from 'buffer';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private url = environment.baseUrl;

  constructor(private http: HttpClient) {}

  register(user: User): Observable<User> {
    return this.http.post<User>(this.url + 'register', user).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(() => new Error('AuthService.register(): error registering user.'));
      })
    );
  }

  login(username: string, password: string): Observable<User> {
    const credentials = this.generateBasicAuthCredentials(username, password);
    const httpOptions = {
      headers: new HttpHeaders({
        Authorization: `Basic ${credentials}`,
        'X-Requested-With': 'XMLHttpRequest',
      }),
    };

    return this.http.get<User>(this.url + 'authenticate', httpOptions).pipe(
      tap((newUser) => {
        localStorage.setItem('credentials', credentials);
        return newUser;
      }),
      catchError((err: any) => {
        console.log(err);
        return throwError(() => new Error('AuthService.login(): error logging in user.'));
      })
    );
  }

  logout(): void {
    localStorage.removeItem('credentials');
  }

  getLoggedInUser(): Observable<User | null> {
    if (!this.checkLogin()) {
      return of(null);
    }
    const httpOptions = {
      headers: new HttpHeaders({
        Authorization: 'Basic ' + this.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      }),
    };
    return this.http.get<User>(this.url + 'authenticate', httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(() => new Error('AuthService.getLoggedInUser(): error retrieving user.'));
      })
    );
  }

  checkLogin(): boolean {
    return !!localStorage.getItem('credentials');
  }

  getUsername(): string | null {
    const credentials = this.getCredentials();
    if (!credentials) {
      return null;
    }
    const decoded = Buffer.from(credentials, 'base64').toString('ascii');
    return decoded.split(':')[0];
  }

  generateBasicAuthCredentials(username: string, password: string): string {
    return Buffer.from(`${username}:${password}`).toString('base64');
  }

  getCredentials(): string | null {
    return localStorage.getItem('credentials');
  }
}

