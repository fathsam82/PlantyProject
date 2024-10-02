import { PostService } from './../../services/post.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Post } from 'src/app/models/post';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrl: './post.component.css',
})
export class PostComponent implements OnInit {

  errorMessage: string = '';

  posts: Post[] | undefined;

  constructor(private postService: PostService, private router: Router) {}

  ngOnInit() {
    this.reload();
  }

  reload() {
    this.errorMessage = '';
    this.postService.index().subscribe({
      next: (posts) => {
        console.log(posts);
        this.posts = posts;
      },
      error: (retrievingPostsError) => {
        console.error('PostComponent.reload: error loading posts');
        console.error(retrievingPostsError);
        console.error(this.posts);
      },
    });
  }
}
