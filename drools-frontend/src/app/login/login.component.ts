import { Component, OnInit } from '@angular/core';
import { LoginService } from './service/login.service';
import jwt_decode from 'jwt-decode';
import { Router } from '@angular/router';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  username: string = '';
  password: string = '';

  constructor(private router: Router, private loginService: LoginService) {}

  login(): void {
    this.loginService
      .createUser({
        username: this.username,
        password: this.password,
      })
      .subscribe((data: any) => {
        var role = this.findUserRole(data.accessToken);
        if (role !== undefined) {
          sessionStorage.setItem('role', role);
          sessionStorage.setItem('isLoggedIn', 'true');
          sessionStorage.setItem('token', data.accessToken);
          let user: any;
          user = jwt_decode(data.accessToken);
          sessionStorage.setItem('username', user.sub);
          this.loginService.emitLogin();
          if (role == 'ROLE_ADMIN') {
            this.router.navigate(['templates']);
          } else if (role === 'ROLE_USER') {
            this.router.navigate(['recommendations']);
          }
          console.log(role + ' je uloga');
        }
      });
  }

  findUserRole(token: any) {
    let user: any;

    if (token) {
      user = jwt_decode(token);
    }
    console.log(user);
    if (user !== undefined) {
      if (user.roles.includes('ADMIN')) {
        return 'ROLE_ADMIN';
      } else {
        return 'ROLE_USER';
      }
    }
    return undefined;
  }

  ngOnInit(): void {}
}
