import { Injectable, Output } from '@angular/core';
import { EventEmitter } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class LogoutService {
  @Output() logout: EventEmitter<any> = new EventEmitter();

  constructor() {}

  logoutFunction = () => {
    this.logout.emit();
  };
}
