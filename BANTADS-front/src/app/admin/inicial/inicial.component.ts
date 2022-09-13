import { Component, OnInit } from '@angular/core';
import { Gerente } from 'src/app/shared';
import { AdminService } from '../services';

@Component({
  selector: 'app-inicial',
  templateUrl: './inicial.component.html',
  styleUrls: ['./inicial.component.css']
})
export class InicialComponent implements OnInit {

  gerentes: Gerente[] = [];

  constructor(private adminService: AdminService) { }

  ngOnInit(): void {
    this.listarTodosGerentes();
  }

  listarTodosGerentes(): void {
    this.adminService.listarTodosGerentes()
      .subscribe((gerentes: Gerente[]) => {
        this.gerentes = gerentes;
      });
  }
}
