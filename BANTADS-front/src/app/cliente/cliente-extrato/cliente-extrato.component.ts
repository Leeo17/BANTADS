import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { LoginService } from '../../auth';
import { ContaService } from '../../conta';
import { Cliente, Conta, ExtratosPorDia, TipoMovimentacao } from '../../shared';
import { ClienteService } from '../services';

@Component({
  selector: 'app-cliente-extrato',
  templateUrl: './cliente-extrato.component.html',
  styleUrls: ['./cliente-extrato.component.css']
})
export class ClienteExtratoComponent implements OnInit {
  @ViewChild('formExtrato') formExtrato!: NgForm;

  public dataInicioExtrato = new Date();
  public dataFimExtrato = new Date();
  public mostrarExtrato = false;
  public cliente: Cliente = new Cliente();
  public conta: Conta = new Conta();
  public isLoading = true;
  public todosExtratosPorDia: ExtratosPorDia[] = [];

  public get dataInvalida(): boolean {
    return this.dataInicioExtrato > this.dataFimExtrato;
  }

  constructor(
    private contaService: ContaService,
    private clienteService: ClienteService,
    private loginService: LoginService
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

  public consultarExtrato(): void {
    if (this.formExtrato.valid && !this.dataInvalida) {
      this.contaService
        .buscarExtratoConta(this.conta.numero!, this.dataInicioExtrato, this.dataFimExtrato)
        .subscribe((extratosPorDia: ExtratosPorDia[]) => {
          this.mostrarExtrato = true;
          this.todosExtratosPorDia = extratosPorDia;
        });
    }
  }

  public getRowBackgroundColor(tipoMovimentacao?: string): string {
    if (tipoMovimentacao == TipoMovimentacao.Deposito) {
      return "background-color: #769af9;"
    }
    return "background-color: #fa5f7b;"
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
}
