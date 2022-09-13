import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { CurrencyMaskConfig, CurrencyMaskModule, CURRENCY_MASK_CONFIG } from 'ng2-currency-mask';
import { NgxMaskModule } from 'ngx-mask';
import { SharedModule } from '../shared';
import { ClienteAutocadastroComponent } from './cliente-autocadastro/cliente-autocadastro.component';
import { ClienteDepositarComponent } from './cliente-depositar/cliente-depositar.component';
import { ClienteExtratoComponent } from './cliente-extrato/cliente-extrato.component';
import { ClienteSaqueComponent } from './cliente-saque/cliente-saque.component';
import { ClienteTransferenciaComponent } from './cliente-transferencia/cliente-transferencia.component';
import { ModalSaldoComponent } from './modal-saldo/modal-saldo.component';
import { ClienteService } from './services/cliente.service';

export const CustomCurrencyMaskConfig: CurrencyMaskConfig = {
  align: "left",
  allowNegative: false,
  decimal: ",",
  precision: 2,
  prefix: "R$ ",
  suffix: "",
  thousands: "."
};

@NgModule({
  declarations: [
    ClienteAutocadastroComponent,
    ClienteDepositarComponent,
    ClienteSaqueComponent,
    ClienteTransferenciaComponent,
    ClienteExtratoComponent,
    ModalSaldoComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    RouterModule,
    NgxMaskModule.forRoot(),
    CurrencyMaskModule,
    SharedModule
  ],
  providers: [
    ClienteService,
    { provide: CURRENCY_MASK_CONFIG, useValue: CustomCurrencyMaskConfig }
  ]
})
export class ClienteModule { }
