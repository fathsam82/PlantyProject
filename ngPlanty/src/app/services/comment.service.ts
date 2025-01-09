import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CommentService {
  private url = environment.baseUrl + 'api/comments'

  constructor() { }
}
