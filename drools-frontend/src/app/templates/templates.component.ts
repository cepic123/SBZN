import { Component, OnInit } from '@angular/core';
import { TemplatesService } from './service/templates.service';

@Component({
  selector: 'app-templates',
  templateUrl: './templates.component.html',
  styleUrls: ['./templates.component.css'],
})
export class TemplatesComponent implements OnInit {
  low: number = 0;
  mid: number = 0;
  high: number = 0;
  path: string = 'Popularity';

  constructor(private templatesService: TemplatesService) {}

  generateTemplate(): void {
    this.templatesService
      .generateTemplate(
        {
          low: this.low,
          mid: this.mid,
          high: this.high,
        },
        this.path
      )
      .subscribe((data) => {
        alert(data);
      });
  }

  ngOnInit(): void {}
}
