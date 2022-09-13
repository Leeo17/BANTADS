import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { LoginService } from '../../auth';
import { ContaService } from '../../conta';
import { Cliente, Conta } from '../../shared';
import { ClienteService } from '../services';

@Component({
  selector: 'app-cliente-transferencia',
  templateUrl: './cliente-transferencia.component.html',
  styleUrls: ['./cliente-transferencia.component.css']
})
export class ClienteTransferenciaComponent implements OnInit {
  @ViewChild('formTransferencia') formTransferencia!: NgForm;

  public cliente: Cliente = new Cliente();
  public conta: Conta = new Conta();
  public isLoading = true;
  public valorTransferencia = 0;
  public numeroContaDestino = 0;

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

  public transferir(): void {
    if (this.formTransferencia.form.valid) {
      this.contaService.transferir(this.conta.numero!, this.numeroContaDestino, this.valorTransferencia)
        .subscribe({
          next: () => {
            alert('Transferência realizada com sucesso.');
            this.formTransferencia.form.reset();
            this.buscarInformacoesConta();
          },
          error: (error) => {
            if (error.status == 404) {
              alert('Conta de destino não encontrada.');
            } else {
              alert('Erro ao realizar transferência.');
            }
          }
        });
    }
  }
}
