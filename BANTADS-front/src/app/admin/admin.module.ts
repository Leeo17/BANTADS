import { NgModule } from '@angular/core';

import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

import { CommonModule } from '@angular/common';
import { NgxMaskModule } from 'ngx-mask';
import { SharedModule } from '../shared';
import { EditarGerenteComponent } from './editar/editar-gerente.component';
import { GerentesComponent } from './gerentes/gerentes.component';
import { InicialComponent } from './inicial/inicial.component';
import { NovoGerenteComponent } from './novo-gerente/novo-gerente.component';

@NgModule({
  declarations: [
    EditarGerenteComponent,
    GerentesComponent,
    InicialComponent,
    NovoGerenteComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    RouterModule,
    NgxMaskModule,
    SharedModule
  ]
})
export class AdminModule { }
