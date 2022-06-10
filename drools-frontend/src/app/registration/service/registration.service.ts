import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserDTO } from 'src/app/model/UserDTO';

@Injectable({
  providedIn: 'root',
})
export class RegistrationService {
  constructor(private http: HttpClient) {}

  createUser(user: UserDTO): Observable<UserDTO[]> {
    return this.http.post<UserDTO[]>('/api/users', user);
  }
}
