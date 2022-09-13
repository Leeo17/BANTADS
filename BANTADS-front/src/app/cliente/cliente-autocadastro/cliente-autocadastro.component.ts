import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Cliente, EnderecoCliente } from '../../shared';
import { ClienteService } from '../services';

@Component({
  selector: 'app-cliente-autocadastro',
  templateUrl: './cliente-autocadastro.component.html',
  styleUrls: ['./cliente-autocadastro.component.css']
})
export class ClienteAutocadastroComponent implements OnInit {
  @ViewChild('formCliente') formCliente!: NgForm;
  public message!: string;
  public enderecoCliente = new EnderecoCliente();
  public cliente = new Cliente();
  public estados: string[] = [
    "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA",
    "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"
  ];

  constructor(
    private router: Router,
    private clienteService: ClienteService
  ) { }

  ngOnInit(): void {
  }

  public registrarCliente(): void {
    if (this.formCliente.form.valid) {
      this.cliente.endereco = this.enderecoCliente;

      this.clienteService.inserir(this.cliente).subscribe({
        next: () => {
          alert('Cadastro de cliente enviado para aprovação.');
        },
        error: () => {
          alert('Erro ao cadastrar novo cliente.');
        },
        complete: () => {
          this.router.navigate(['/login']);
        }
      });
    }
  }
}
