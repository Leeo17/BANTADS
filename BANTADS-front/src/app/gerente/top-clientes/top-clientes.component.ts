import { Component, OnInit } from '@angular/core';
import { ContaRead } from 'src/app/shared';
import { LoginService } from '../../auth';
import { ContaService } from '../../conta';
import { GerenteService } from '../services';

@Component({
  selector: 'app-top-clientes',
  templateUrl: './top-clientes.component.html',
  styleUrls: ['./top-clientes.component.css']
})
export class TopClientesComponent implements OnInit {
  public contas: ContaRead[] = [];
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
            this.contaService.buscarTopContasGerente(gerente.id)
              .subscribe((contas: ContaRead[]) => {
                this.isLoading = true;
                this.contas = contas;
              });
          }
        });
    }
  }
}
