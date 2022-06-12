import { Routes } from '@angular/router';
import { CreateGameComponent } from '../create-game/create-game.component';
import { LoginComponent } from '../login/login.component';
import { LogoutComponent } from '../logout/logout.component';
import { RecommendationsComponent } from '../recommendations/recommendations.component';
import { RegistrationComponent } from '../registration/registration.component';
import { TemplatesComponent } from '../templates/templates.component';
import { TopListComponent } from '../top-list/top-list.component';

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
  {
    path: 'top-list',
    component: TopListComponent,
  },
  {
    path: 'create-game',
    component: CreateGameComponent,
  },
];