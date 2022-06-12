import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ParametersDTO } from 'src/app/model/ParametersDTO';
import { TopListDTO } from 'src/app/model/toplist';

@Injectable({
  providedIn: 'root',
})
export class TopListService {
  constructor(private http: HttpClient) {}

  getTopList(): Observable<TopListDTO[]> {
    return this.http.post<TopListDTO[]>('/api/game/topList', null);
  }

  setReview(gameName: String, score: number): Observable<TopListDTO[]> {
    return this.http.post<TopListDTO[]>('/api/review/save', {
      "userName" : sessionStorage.getItem("username"),
      "gameName" : gameName,
      "score": score
    });
  }
}
