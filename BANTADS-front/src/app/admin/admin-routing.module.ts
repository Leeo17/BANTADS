import { Routes } from '@angular/router';
import { AuthGuard } from '../auth';
import { TipoPerfil } from '../shared';
import { EditarGerenteComponent } from './editar';
import { GerentesComponent } from './gerentes';
import { InicialComponent } from './inicial';
import { NovoGerenteComponent } from './novo-gerente/novo-gerente.component';

export const AdminRoutes: Routes = [
  {
    path: 'admin',
    redirectTo: 'admin/inicial'
  },
  {
    path: 'admin/inicial',
    component: InicialComponent,
    canActivate: [AuthGuard],
    data: {
      role: TipoPerfil.Administrador
    }
  },
  {
    path: 'admin/gerentes',
    component: GerentesComponent,
    canActivate: [AuthGuard],
    data: {
      role: TipoPerfil.Administrador
    }
  },
  {
    path: 'admin/gerentes/novo',
    component: NovoGerenteComponent,
    canActivate: [AuthGuard],
    data: {
      role: TipoPerfil.Administrador
    }
  },
  {
    path: 'admin/gerentes/editar/:id',
    component: EditarGerenteComponent,
    canActivate: [AuthGuard],
    data: {
      role: TipoPerfil.Administrador
    }
  },
];