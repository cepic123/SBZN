import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TemplateDTO } from 'src/app/model/template';

@Injectable({
  providedIn: 'root',
})
export class TemplatesService {
  constructor(private http: HttpClient) {}

  generateTemplate(templateDto: TemplateDTO, value: string): Observable<any> {
    return this.http.post(`/api/template/generate${value}`, templateDto, {
      responseType: 'text',
    });
  }
}
