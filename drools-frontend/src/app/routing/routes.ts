import { Routes } from '@angular/router';
import { LoginComponent } from '../login/login.component';
import { LogoutComponent } from '../logout/logout.component';
import { RecommendationsComponent } from '../recommendations/recommendations.component';
import { RegistrationComponent } from '../registration/registration.component';
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
  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: 'registration',
    component: RegistrationComponent,
  },
  {
    path: 'logout',
    component: LogoutComponent,
  },
];
