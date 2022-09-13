import { Component, OnInit } from '@angular/core';
import { ContaRead } from 'src/app/shared';
import { LoginService } from '../../auth';
import { ContaService } from '../../conta';
import { GerenteService } from '../services';

@Component({
  selector: 'app-listar-autocadastros',
  templateUrl: './listar-autocadastros.component.html',
  styleUrls: ['./listar-autocadastros.component.css']
})
export class ListarAutocadastrosComponent implements OnInit {
  public contas: ContaRead[] = [];
  public idGerente: string = "";
  public isLoading = true;

  constructor(
    private contaService: ContaService,
    private gerenteService: GerenteService,
    private loginService: LoginService
  ) { }

  ngOnInit(): void {
    let usuarioLogado = this.loginService.usuarioLogado;
    if (usuarioLogado && usuarioLogado.login) {
      this.gerenteService.
        buscarGerentePorLogin(usuarioLogado.login).subscribe((gerente) => {
          if (gerente && gerente.id) {
            this.contaService.buscarContasPendentesGerente(gerente.id)
              .subscribe((contas: ContaRead[]) => {
                this.isLoading = false;
                this.contas = contas;
              });
          }
        });
    }
  }

  aprovar($event: any, conta: ContaRead): void {
    $event.preventDefault();
    if (conta && conta.numero) {
      this.contaService.aprovarContaCliente(conta.numero).subscribe(() => {
        alert('Conta aprovada com sucesso!');
        location.reload();
      });
    }
  }

  recusar($event: any, conta: ContaRead): void {
    $event.preventDefault();
    let motivoReprovacao = window.prompt("Digite o motivo da reprovação:") || "";

    if (conta && conta.numero) {
      this.contaService.recusarContaCliente(conta.numero, motivoReprovacao).subscribe(() => {
        alert('Conta recusada! Um email com a motivação foi enviado ao cliente.');
        location.reload();
      });
    }
  }
}
