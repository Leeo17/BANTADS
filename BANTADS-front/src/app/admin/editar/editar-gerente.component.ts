import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Gerente } from 'src/app/shared';
import { AdminService } from '../services';

@Component({
  selector: 'app-editar-gerente',
  templateUrl: './editar-gerente.component.html',
  styleUrls: ['./editar-gerente.component.css']
})
export class EditarGerenteComponent implements OnInit {
  @ViewChild('formGerente') formGerente!: NgForm;
  public isLoading = true;

  gerente!: Gerente;

  constructor(
    private adminService: AdminService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    let id = this.route.snapshot.params['id'];
    this.adminService.buscarGerentePorId(id).subscribe((gerente: Gerente) => {
      this.gerente = gerente;
      this.isLoading = false;
    });
  }

  atualizar(): void {
    if (this.formGerente.form.valid) {
      this.adminService.atualizarGerente(this.gerente).subscribe({
        next: () => {
          alert('Gerente editado com sucesso.');
          this.router.navigate(['/admin/gerentes']);
        },
        error: () => {
          alert('Erro ao editar gerente.');
        }
      });
    }
  }
}
