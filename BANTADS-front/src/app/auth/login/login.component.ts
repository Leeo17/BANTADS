import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Login, TipoPerfil } from '../../shared';
import { LoginService } from '../services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  @ViewChild('formLogin') formLogin!: NgForm;
  public login = new Login();
  public loading = false;
  public message!: string;

  constructor(
    private loginService: LoginService,
    private router: Router,
    private route: ActivatedRoute,
  ) {
    if (this.loginService.isUserLoggedIn()) {
      if (this.loginService.usuarioLogado.perfil === TipoPerfil.Cliente) {
        this.router.navigate(['/cliente/depositar']);
      }
      if (this.loginService.usuarioLogado.perfil === TipoPerfil.Gerente) {
        this.router.navigate(['/gerente/autocadastros']);
      }
      if (this.loginService.usuarioLogado.perfil === TipoPerfil.Administrador) {
        this.router.navigate(['/admin/gerentes']);
      }
    }
  }

  ngOnInit(): void {
    this.route.queryParams.subscribe((params) => {
      this.message = params['error'];
    });
  }

  public logar(): void {
    this.loading = true;

    if (this.formLogin.form.valid) {
      this.loginService.login(this.login).subscribe({
        next: (output) => {
          this.loading = false;
          this.loginService.setAuthorizationToken(output.token);
          this.loginService.usuarioLogado = output.data;

          if (this.loginService.usuarioLogado.perfil === TipoPerfil.Cliente) {
            this.router.navigate(['/cliente/depositar']);
          };
          if (this.loginService.usuarioLogado.perfil === TipoPerfil.Gerente) {
            this.router.navigate(['/gerente/autocadastros']);
          };
          if (this.loginService.usuarioLogado.perfil === TipoPerfil.Administrador) {
            this.router.navigate(['/admin/inicial']);
          };
        },
        error: () => {
          this.loading = false;
          this.message = 'Usuário/Senha inválidos.';
        }
      });
    }
  }
}
