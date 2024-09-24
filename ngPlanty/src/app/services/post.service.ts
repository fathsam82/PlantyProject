import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PostService {

private url = environment.baseUrl + 'api/post';

  constructor() { }
}
