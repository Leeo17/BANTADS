import { Routes } from '@angular/router';
import { AuthGuard } from '../auth';
import { TipoPerfil } from '../shared';
import { BuscarClienteComponent } from './buscar-cliente';
import { ListarAutocadastrosComponent } from './listar-autocadastros';
import { ListarClientesComponent } from './listar-clientes';
import { TopClientesComponent } from './top-clientes';

export const GerenteRoutes: Routes = [
  {
    path: 'gerente',
    redirectTo: 'gerente/autocadastros'
  },
  {
    path: 'gerente/autocadastros',
    component: ListarAutocadastrosComponent,
    canActivate: [AuthGuard],
    data: {
      role: TipoPerfil.Gerente
    }
  },
  {
    path: 'gerente/clientes',
    component: ListarClientesComponent,
    canActivate: [AuthGuard],
    data: {
      role: TipoPerfil.Gerente
    }
  },
  {
    path: 'gerente/top-clientes',
    component: TopClientesComponent,
    canActivate: [AuthGuard],
    data: {
      role: TipoPerfil.Gerente
    }
  },
  {
    path: 'gerente/busca-cpf',
    component: BuscarClienteComponent,
    canActivate: [AuthGuard],
    data: {
      role: TipoPerfil.Gerente
    }
  }
];