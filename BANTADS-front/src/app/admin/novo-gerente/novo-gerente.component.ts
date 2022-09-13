import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Gerente } from 'src/app/shared';
import { v4 as uuidv4 } from 'uuid';
import { AdminService } from '../services';

@Component({
  selector: 'app-novo-gerente',
  templateUrl: './novo-gerente.component.html',
  styleUrls: ['./novo-gerente.component.css']
})

export class NovoGerenteComponent implements OnInit {

  @ViewChild('formGerente') formGerente!: NgForm;

  gerente!: Gerente;

  constructor(
    private adminService: AdminService,
    private router: Router,
  ) { }

  ngOnInit(): void {
    this.gerente = new Gerente();
  }

  inserir(): void {
    if (this.formGerente.form.valid) {
      this.gerente.id = uuidv4();
      this.adminService.criarGerente(this.gerente).subscribe({
        next: () => {
          alert('Gerente cadastrado com sucesso.');
          this.router.navigate(['/admin/gerentes']);
        },
        error: () => {
          alert('Erro ao cadastrar gerente.');
          this.formGerente.reset();
        }
      });
    }
  }
}