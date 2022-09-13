import { Routes } from '@angular/router';
import { AuthGuard } from '../auth';
import { TipoPerfil } from '../shared';
import { ClienteAutocadastroComponent } from './cliente-autocadastro/cliente-autocadastro.component';
import { ClienteDepositarComponent } from './cliente-depositar/cliente-depositar.component';
import { ClienteExtratoComponent } from './cliente-extrato/cliente-extrato.component';
import { ClienteSaqueComponent } from './cliente-saque/cliente-saque.component';
import { ClienteTransferenciaComponent } from './cliente-transferencia/cliente-transferencia.component';

export const ClienteRoutes: Routes = [
  {
    path: 'cliente',
    redirectTo: 'cliente/depositar'
  },
  {
    path: 'cliente/novo',
    component: ClienteAutocadastroComponent
  },
  {
    path: 'cliente/depositar',
    component: ClienteDepositarComponent,
    canActivate: [AuthGuard],
    data: {
      role: TipoPerfil.Cliente
    }
  },
  {
    path: 'cliente/saque',
    component: ClienteSaqueComponent,
    canActivate: [AuthGuard],
    data: {
      role: TipoPerfil.Cliente
    }
  },
  {
    path: 'cliente/transferencia',
    component: ClienteTransferenciaComponent,
    canActivate: [AuthGuard],
    data: {
      role: TipoPerfil.Cliente
    }
  },
  {
    path: 'cliente/extrato',
    component: ClienteExtratoComponent,
    canActivate: [AuthGuard],
    data: {
      role: TipoPerfil.Cliente
    }
  }
];