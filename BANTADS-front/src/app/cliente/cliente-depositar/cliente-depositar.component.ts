import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Cliente, Conta } from 'src/app/shared';
import { LoginService } from '../../auth';
import { ContaService } from '../../conta';
import { ClienteService } from '../services';

@Component({
  selector: 'app-cliente-depositar',
  templateUrl: './cliente-depositar.component.html',
  styleUrls: ['./cliente-depositar.component.css']
})
export class ClienteDepositarComponent implements OnInit {
  @ViewChild('formDeposito') formDeposito!: NgForm;
  public valorDeposito = 0;
  public cliente: Cliente = new Cliente();
  public conta: Conta = new Conta();
  public isLoading = true;

  constructor(
    private clienteService: ClienteService,
    private loginService: LoginService,
    private contaService: ContaService,
  ) { }

  ngOnInit(): void {
    let usuarioLogado = this.loginService.usuarioLogado;
    if (usuarioLogado && usuarioLogado.login) {
      this.clienteService.buscarPorEmail(usuarioLogado.login)
        .subscribe((cliente: Cliente) => {
          this.cliente = cliente;
          this.buscarInformacoesConta();
        });
    }
  }

  public buscarInformacoesConta() {
    if (this.cliente && this.cliente.id) {
      this.contaService.buscarContaPorIdCliente(this.cliente.id).subscribe((conta: Conta) => {
        this.isLoading = false;
        this.conta = conta;
        this.contaService.contaAtual.next(conta);
      });
    }
  }

  public depositar(): void {
    if (this.formDeposito.form.valid) {
      this.contaService.depositar(this.conta.numero!, this.valorDeposito)
        .subscribe(() => {
          alert('Depósito realizado com sucesso.');
          this.formDeposito.form.reset();
          this.buscarInformacoesConta();
        });
    }
  }
}
