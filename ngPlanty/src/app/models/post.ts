import { User } from "./user";

export class Post {
id: number;
title: string;
content: string;
createdAt: string;
user: User;
comments: Comment[]|undefined;
enabled: boolean;

constructor(
  id: number = 0,
  title: string = '',
  content: string = '',
  createdAt: string = '',
  user: User = new User(),
  comments: Comment[] = [],
  enabled: boolean = true

) {
  this.id = id;
  this.title = title;
  this.content = content;
  this.createdAt = createdAt;
  this.user = user;
  this.comments = comments;
  this.enabled = enabled;
}

}
