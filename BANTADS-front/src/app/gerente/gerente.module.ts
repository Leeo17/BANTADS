import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

import { NgxMaskModule } from 'ngx-mask';
import { SharedModule } from '../shared';
import { BuscarClienteComponent } from './buscar-cliente/buscar-cliente.component';
import { ListarAutocadastrosComponent } from './listar-autocadastros';
import { ListarClientesComponent } from './listar-clientes';
import { ModalClienteComponent } from './modal-cliente';
import { ModalFiltrarClienteComponent } from './modal-filtrar-cliente/modal-filtrar-cliente.component';
import { TopClientesComponent } from './top-clientes/top-clientes.component';

@NgModule({
  declarations: [
    ListarAutocadastrosComponent,
    ListarClientesComponent,
    ModalClienteComponent,
    BuscarClienteComponent,
    ModalFiltrarClienteComponent,
    TopClientesComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    NgxMaskModule,
    SharedModule
  ]
})
export class GerenteModule { }
