import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Cliente, Conta } from 'src/app/shared';
import { LoginService } from '../../auth';
import { ContaService } from '../../conta';
import { ClienteService } from '../services';

@Component({
  selector: 'app-cliente-saque',
  templateUrl: './cliente-saque.component.html',
  styleUrls: ['./cliente-saque.component.css'],
})
export class ClienteSaqueComponent implements OnInit {
  @ViewChild('formSaque') formSaque!: NgForm;
  public valorSaque = 0;
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

  public sacar(): void {
    if (this.formSaque.form.valid) {
      this.contaService.sacar(this.conta.numero!, this.valorSaque)
        .subscribe({
          next: () => {
            alert('Saque realizado com sucesso.');
            this.formSaque.form.reset();
            this.buscarInformacoesConta();
          },
          error: () => {
            alert('Erro ao realizar saque.');
          }
        });
    }
  }
}
