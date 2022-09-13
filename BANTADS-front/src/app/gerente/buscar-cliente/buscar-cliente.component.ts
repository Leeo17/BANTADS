import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { LoginService } from '../../auth';
import { ContaService } from '../../conta';
import { ContaRead } from '../../shared';
import { ModalClienteComponent } from '../modal-cliente';
import { GerenteService } from '../services';

@Component({
  selector: 'app-buscar-cliente',
  templateUrl: './buscar-cliente.component.html',
  styleUrls: ['./buscar-cliente.component.css']
})
export class BuscarClienteComponent implements OnInit {
  public cpfCliente: string = "";
  public idGerente = "";
  public isLoading = true;

  constructor(
    private gerenteService: GerenteService,
    private contaService: ContaService,
    private loginService: LoginService,
    private modalService: NgbModal
  ) { }

  ngOnInit(): void {
    let usuarioLogado = this.loginService.usuarioLogado;
    if (usuarioLogado && usuarioLogado.login) {
      this.gerenteService.buscarGerentePorLogin(usuarioLogado.login)
        .subscribe((gerente) => {
          if (gerente && gerente.id) {
            this.isLoading = false;
            this.idGerente = gerente.id;
          }
        });
    }
  }

  buscarPorCPF(): void {
    if (this.idGerente && this.cpfCliente) {
      this.contaService.buscarContaClientePorCpf(this.idGerente, this.cpfCliente)
        .subscribe({
          next: (conta: ContaRead) => {
            if (conta) {
              const modalRef = this.modalService.open(ModalClienteComponent);
              modalRef.componentInstance.contaCliente = conta;
            }
          },
          error: () => {
            alert("Cliente não encontrado!");
          }
        });
    }
  }
}
