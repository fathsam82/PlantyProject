import { PostService } from './../../services/post.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Post } from 'src/app/models/post';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrl: './post.component.css',
})
export class PostComponent implements OnInit {
  errorMessage: string = '';

  posts: Post[] | undefined;

  loggedInUser: User = new User();

  constructor(
    private authService: AuthService,
    private postService: PostService,
    private router: Router
  ) {}

  ngOnInit() {
    // this.authService.getLoggedInUser().subscribe({
    //   next: (user) => {
    //     if (user) {
    //       this.loggedInUser = user;
    //     } else {
    //       console.error('No user logged in');
    //     }
    //   },
    //   error: (fail) => {
    //     console.error('ngOnInit(): Error getting user');
    //     console.error(fail);
    //   },
    // });

    if (this.authService.checkLogin()) {
      this.authService.getLoggedInUser().subscribe({
        next: (user) => {
          if (user) {
            this.loggedInUser = user;
          } else {
            console.error('No user logged in');
          }
        },
        error: (fail) => {
          console.error('ngOnInit(): Error getting user');
          console.error(fail);
        },
      });
      this.reload();
    }
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
