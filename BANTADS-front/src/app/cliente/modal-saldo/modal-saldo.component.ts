import { Component, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { ContaService } from '../../conta';
import { Conta } from '../../shared';

@Component({
  selector: 'app-modal-saldo',
  templateUrl: './modal-saldo.component.html',
  styleUrls: ['./modal-saldo.component.css']
})
export class ModalSaldoComponent implements OnInit {
  public conta: Conta = new Conta();

  constructor(
    public activeModal: NgbActiveModal,
    public contaService: ContaService
  ) { }

  ngOnInit(): void {
    this.conta = this.contaService.contaAtual.value;
  }
}
