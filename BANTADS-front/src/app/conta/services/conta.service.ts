import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { environment } from '../../../environments/environment';
import { Conta } from '../../shared';

@Injectable({
  providedIn: 'root'
})
export class ContaService {
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  public contaAtual = new BehaviorSubject<Conta>(new Conta());

  constructor(private http: HttpClient) { }

  buscarContasPendentesGerente(idGerente: string) {
    return this.http.get<any>(`${environment.api}/contas/gerente/${idGerente}?aprovada=null`, this.httpOptions);
  }

  buscarContasAprovadasGerente(idGerente: string, cpf?: string, nome?: string) {
    let params = new HttpParams().set('aprovada', true);
    if (cpf) {
      params = params.set('cpf', cpf);
    }
    if (nome) {
      params = params.set('nome', nome);
    }

    return this.http.get<any>(`${environment.api}/contas/gerente/${idGerente}`, {
      params,
      headers: this.httpOptions.headers
    });
  }

  buscarTopContasGerente(idGerente: string) {
    return this.http.get<any>(`${environment.api}/contas/gerente/${idGerente}/top`, this.httpOptions);
  }

  buscarContaClientePorCpf(idGerente: string, cpfCliente: string) {
    return this.http.get<any>(`${environment.api}/contas/gerente/${idGerente}/cliente/${cpfCliente}`, this.httpOptions);
  }

  aprovarContaCliente(numeroConta: number) {
    return this.http.post<any>(`${environment.api}/contas/${numeroConta}/aprovada`, null, this.httpOptions);
  }

  recusarContaCliente(numeroConta: number, motivoReprovacao: string) {
    return this.http.post<any>(`${environment.api}/contas/${numeroConta}/reprovada?motivo=${motivoReprovacao}`,
      null, this.httpOptions);
  }

  buscarContaPorIdCliente(idCliente: string) {
    return this.http.get<any>(`${environment.api}/contas/cliente/${idCliente}`, this.httpOptions);
  }

  depositar(numeroConta: number, valor: number) {
    return this.http.post<any>(`${environment.api}/contas/${numeroConta}/deposito?valor=${valor}`, null, this.httpOptions);
  }

  sacar(numeroConta: number, valor: number) {
    return this.http.post<any>(`${environment.api}/contas/${numeroConta}/saque?valor=${valor}`, null, this.httpOptions);
  }

  transferir(numeroContaOrigem: number, numeroContaDestino: number, valor: number) {
    let params = new HttpParams()
      .set('numeroDestino', numeroContaDestino)
      .set('valor', valor);

    return this.http.post<any>(`${environment.api}/contas/${numeroContaOrigem}/transferencia`, null, {
      params,
      headers: this.httpOptions.headers
    });
  }

  buscarExtratoConta(numeroConta: number, dataInicio: Date, dataFim: Date) {
    let params = new HttpParams()
      .set('dataInicio', dataInicio.toString())
      .set('dataFim', dataFim.toString());

    return this.http.get<any>(`${environment.api}/contas/${numeroConta}/extrato`, {
      params,
      headers: this.httpOptions.headers
    });
  }
}
