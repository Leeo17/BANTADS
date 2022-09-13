import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { Cliente } from '../../shared/models/cliente.model';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private http: HttpClient) { }

  public inserir(cliente: Cliente): Observable<any> {
    return this.http.post<any>(`${environment.api}/clientes`, cliente, this.httpOptions);
  }

  public buscarPorEmail(email: string): Observable<any> {
    return this.http.get<any>(`${environment.api}/clientes?email=${email}`, this.httpOptions);
  }
}
