import { Routes } from '@angular/router';
import { RecommendationsComponent } from '../recommendations/recommendations.component';
import { TemplatesComponent } from '../templates/templates.component';

export const routes: Routes = [
  {
    path: 'recommendations',
    component: RecommendationsComponent,
  },
  {
    path: 'templates',
    component: TemplatesComponent,
  },
];
