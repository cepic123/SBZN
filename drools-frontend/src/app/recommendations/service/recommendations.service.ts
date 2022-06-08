import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ParametersDTO } from 'src/app/model/ParametersDTO';
import { TopListDTO } from 'src/app/model/toplist';

@Injectable({
  providedIn: 'root',
})
export class RecommendationsService {
  constructor(private http: HttpClient) {}

  getRecommendations(parameters: ParametersDTO): Observable<TopListDTO[]> {
    return this.http.post<TopListDTO[]>(
      '/api/game/recommendations/1',
      parameters
    );
  }
}
