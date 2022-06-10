import { Component, OnInit } from '@angular/core';
import { RegistrationService } from './service/registration.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css'],
})
export class RegistrationComponent implements OnInit {
  name: string = '';
  lastname: string = '';
  username: string = '';
  password: string = '';

  constructor(private registrationService: RegistrationService) {}

  createUser(): void {
    this.registrationService
      .createUser({
        name: this.name,
        lastname: this.lastname,
        username: this.username,
        password: this.password,
      })
      .subscribe((data) => {
        console.log(data);
        alert('Success');
      });
  }

  ngOnInit(): void {}
}
