import { Component, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-modal-filtrar-cliente',
  templateUrl: './modal-filtrar-cliente.component.html',
  styleUrls: ['./modal-filtrar-cliente.component.css']
})
export class ModalFiltrarClienteComponent {
  @ViewChild('formFiltrar') formFiltrar!: NgForm;

  public parametrosFiltrar = {
    cpf: "",
    nome: "",
  };

  constructor(public activeModal: NgbActiveModal) { }

  public filtrarClientes(): void {
    this.activeModal.close(this.parametrosFiltrar);
  }
}

