import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../login/service/login.service';
import { LogoutService } from '../logout/service/logout.service';
import { NavbarItem } from '../model/NavbarItem';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
})
export class NavbarComponent implements OnInit {
  items: NavbarItem[] = [
    { name: 'Login', route: 'login' },
    { name: 'Registration', route: 'registration' },
  ];
  constructor(
    private router: Router,
    private loginService: LoginService,
    private logoutService: LogoutService
  ) {
    this.loginService.getUserRole.subscribe(() => {
      this.setNavbarItems();
    });
    this.logoutService.logout.subscribe(() => {
      this.setLogoutItems();
    });
  }

  routeTo(route: String): void {
    this.router.navigate([route]);
  }

  setNavbarItems(): void {
    this.items = [
      { name: 'Logout', route: 'logout' },
      { name: 'Templates', route: 'templates' },
      { name: 'Recommendations', route: 'recommendations' },
    ];
  }

  setLogoutItems(): void {
    this.items = [
      { name: 'Login', route: 'login' },
      { name: 'Registration', route: 'registration' },
    ];
  }

  ngOnInit(): void {}
}
