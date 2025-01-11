import { Comment } from './../../models/comment';
import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { CommentService } from 'src/app/services/comment.service';

@Component({
  selector: 'app-post-detail',
  templateUrl: './post-detail.component.html',
  styleUrl: './post-detail.component.css',
})
export class PostDetailComponent implements OnInit {

  commentList: Comment[] | undefined;
  constructor(private authService: AuthService, private commentService: CommentService) {}

  ngOnInit() {}

  getCommentByPost(){
    this.commentService.getCommentsByPost().subscribe({
      next: (commentList) => {
        console.log(commentList);
        this.commentList = commentList;
      },
      error: (somethingBad) => {
        console.error(
          'PostDetailComponent.getCommentByPost: error loading comment'
        );
        console.log(somethingBad);
        console.log(this.commentList);
      }
    });
  }
}
