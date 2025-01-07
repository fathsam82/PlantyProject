import { Post } from './post';
import { User } from './user';

export class Comment {
  id: number;
  user: User;
  post: Post;
  content: String;
  createdAt: String;
  enabled: boolean;

  constructor(
    id: number = 0,
    user: User = new User(),
    post: Post = new Post(),
    content: String = '',
    createdAt: String = '',
    enabled: boolean = false
  ) {
    this.id = id;
    this.user = user;
    this.post = post;
    this.content = content;
    this.createdAt = createdAt;
    this.enabled = enabled;
  }
}
