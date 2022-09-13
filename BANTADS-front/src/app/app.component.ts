import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { LoginService } from './auth';
import { ModalSaldoComponent } from './cliente';
import { TipoPerfil, Usuario } from './shared';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  public title = 'BANTADS';

  constructor(
    private router: Router,
    private loginService: LoginService,
    private modalService: NgbModal
  ) { }

  public get isUsuarioLogado(): boolean {
    return this.loginService.isUserLoggedIn();
  }

  public get usuarioLogado(): Usuario {
    return this.loginService.usuarioLogado;
  }

  public get usuarioCliente(): boolean {
    return this.usuarioLogado.perfil === TipoPerfil.Cliente;
  }

  public get usuarioGerente(): boolean {
    return this.usuarioLogado.perfil === TipoPerfil.Gerente;
  }

  public get usuarioAdmin(): boolean {
    return this.usuarioLogado.perfil === TipoPerfil.Administrador;
  }

  public abrirModalSaldo($event: any) {
    $event.preventDefault();
    const modalRef = this.modalService.open(ModalSaldoComponent);
  }

  public logout($event: any): void {
    $event.preventDefault();
    this.loginService.logout().subscribe(() => {
      this.loginService.usuarioLogado = null;
      this.loginService.setAuthorizationToken("");
      this.router.navigate(["/login"]);
    });
  }
}
