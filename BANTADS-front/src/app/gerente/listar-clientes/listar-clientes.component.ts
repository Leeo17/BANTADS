import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ContaRead } from 'src/app/shared';
import { LoginService } from '../../auth';
import { ContaService } from '../../conta';
import { ModalClienteComponent } from '../modal-cliente';
import { ModalFiltrarClienteComponent } from '../modal-filtrar-cliente';
import { GerenteService } from '../services';


@Component({
  selector: 'app-listar-clientes',
  templateUrl: './listar-clientes.component.html',
  styleUrls: ['./listar-clientes.component.css']
})
export class ListarClientesComponent implements OnInit {
  public contas: ContaRead[] = [];
  public isLoading = true;
  public idGerente = "";
  public resultadosFiltrados = false;

  constructor(
    private contaService: ContaService,
    private loginService: LoginService,
    private gerenteService: GerenteService,
    private modalService: NgbModal
  ) { }

  ngOnInit(): void {
    let usuarioLogado = this.loginService.usuarioLogado;
    if (usuarioLogado && usuarioLogado.login) {
      this.gerenteService.buscarGerentePorLogin(usuarioLogado.login)
        .subscribe((gerente) => {
          if (gerente && gerente.id) {
            this.idGerente = gerente.id;
            this.buscarClientes();
          }
        });
    }
  }

  verDetalhes(conta: ContaRead) {
    const modalRef = this.modalService.open(ModalClienteComponent);
    modalRef.componentInstance.contaCliente = conta;
  }

  abrirModalFiltrarCliente($event: any) {
    $event.preventDefault();
    this.modalService.open(ModalFiltrarClienteComponent).closed
      .subscribe((resultado: { cpf: string, nome: string }) => {
        if (resultado) {
          this.isLoading = true;
          this.resultadosFiltrados = true;
          this.buscarClientes(resultado.cpf, resultado.nome);
        }
      });
  }

  buscarClientes(cpf?: string, nome?: string): void {
    this.contaService.buscarContasAprovadasGerente(this.idGerente, cpf, nome)
      .subscribe((contas: ContaRead[]) => {
        this.isLoading = false;
        this.contas = contas;
      });
  }

  removerFiltros($event: any): void {
    $event.preventDefault();
    this.resultadosFiltrados = false;
    this.buscarClientes();
  }
}
