import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { GameDTO } from 'src/app/model/GameDTO';
import { TopListDTO } from 'src/app/model/toplist';

@Injectable({
  providedIn: 'root',
})
export class CreateGameService {
  constructor(private http: HttpClient) {}

  save(gameDTO: GameDTO): Observable<TopListDTO[]> {
    return this.http.post<TopListDTO[]>(
      '/api/game/save',
      gameDTO
    );
  }
}
