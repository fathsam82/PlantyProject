import { Component } from '@angular/core';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-register',

  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {

  newUser: User = new User();

  register(user: User) {
    console.log('Registering :');
    console.log(user);
  }

}
