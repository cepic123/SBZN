import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginDTO } from 'src/app/model/LoginDTO';
import { Token } from '@angular/compiler';
import { Injectable, Output, EventEmitter } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  @Output() getUserRole: EventEmitter<any> = new EventEmitter();

  constructor(private http: HttpClient) {}

  createUser(login: LoginDTO): Observable<any[]> {
    return this.http.post<Token[]>('/api/auth/login', login);
  }

  emitLogin = () => {
    this.getUserRole.emit();
  };
}
