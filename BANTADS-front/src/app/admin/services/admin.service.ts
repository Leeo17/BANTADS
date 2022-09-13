import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Gerente } from 'src/app/shared';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AdminService {
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(
    private http: HttpClient,
  ) { }

  public listarTodosGerentes(): Observable<any> {
    return this.http.get<any>(`${environment.api}/gerentes`, this.httpOptions);
  }

  public criarGerente(gerente: Gerente): Observable<any> {
    return this.http.post<any>(`${environment.api}/gerentes`, gerente, this.httpOptions);
  }

  public buscarGerentePorId(id: string): Observable<any> {
    return this.http.get<any>(`${environment.api}/gerentes/${id}`, this.httpOptions);
  }

  public atualizarGerente(gerente: Gerente): Observable<any> {
    return this.http.put<any>(`${environment.api}/gerentes/${gerente.id}`, gerente, this.httpOptions);
  }

  public removerGerente(id: string): Observable<any> {
    return this.http.delete<any>(`${environment.api}/gerentes/${id}`, this.httpOptions);
  }
}
