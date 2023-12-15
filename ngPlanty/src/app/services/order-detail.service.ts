import { AuthService } from './auth.service';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class OrderDetailService {

  private url = environment.baseUrl + "api/orderDetails";

  constructor(private httpClient: HttpClient, private authService:AuthService) { }
}
