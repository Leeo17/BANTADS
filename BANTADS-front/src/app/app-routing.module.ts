import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminRoutes } from './admin';
import { LoginRoutes } from './auth';
import { ClienteRoutes } from './cliente';
import { GerenteRoutes } from './gerente';

const routes: Routes = [{
  path: '',
  redirectTo: 'login',
  pathMatch: 'full'
}, 
...LoginRoutes,
...ClienteRoutes,
...GerenteRoutes,
...AdminRoutes
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
