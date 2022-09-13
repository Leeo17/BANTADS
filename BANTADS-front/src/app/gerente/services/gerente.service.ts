import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Gerente } from 'src/app/shared';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class GerenteService {
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(
    private http: HttpClient
  ) { }

  public buscarGerentePorLogin(login: string): Observable<Gerente> {
    return this.http.get<any>(`${environment.api}/gerentes/email/${login}`, this.httpOptions);
  }
}
