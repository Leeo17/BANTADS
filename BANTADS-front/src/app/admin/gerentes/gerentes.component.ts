import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

import { Gerente } from 'src/app/shared';
import { AdminService } from '../services';

@Component({
  selector: 'app-gerentes',
  templateUrl: './gerentes.component.html',
  styleUrls: ['./gerentes.component.css']
})
export class GerentesComponent implements OnInit {
  gerentes: Gerente[] = [];

  constructor(private adminService: AdminService) { }

  ngOnInit(): void {
    this.listarTodosGerentes();
  }

  listarTodosGerentes(): void {
    this.adminService.listarTodosGerentes().subscribe((gerentes: Gerente[]) => {
      this.gerentes = gerentes;
    });
  }

  remover($event: any, gerente: Gerente): void {
    $event.preventDefault();
    if (confirm(`Deseja realmente remover o gerente ${gerente.nome}?`)) {
      this.adminService.removerGerente(gerente.id!).subscribe({
        next: () => {
          this.listarTodosGerentes();
        },
        error: (error: HttpErrorResponse) => {
          if (error.status === 403) {
            alert("Esse gerente não pode ser removido, pois existem contas vinculadas à ele.");
          }
        }
      });
    }
  }
}
