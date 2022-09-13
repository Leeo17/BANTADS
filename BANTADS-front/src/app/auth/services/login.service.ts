import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import jwtDecode from 'jwt-decode';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { Login, Usuario } from '../../shared';

const LS_CHAVE_TOKEN: string = "token";
const LS_USUARIO_LOGADO: string = "usuario-logado";

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private http: HttpClient) { }

  public set usuarioLogado(usuario: Usuario | null) {
    localStorage.setItem(LS_USUARIO_LOGADO, JSON.stringify(usuario));
  }

  public get usuarioLogado(): Usuario {
    let usu = localStorage.getItem(LS_USUARIO_LOGADO);
    return (usu ? JSON.parse(usu) : null);
  }

  public logout(): Observable<unknown> {
    return this.http.post(`${environment.api}/logout`, null, this.httpOptions);
  }

  public login(login: Login): Observable<any> {
    return this.http.post<any>(`${environment.api}/login`, login, this.httpOptions);
  }

  public getAuthorizationToken() {
    const token = localStorage.getItem(LS_CHAVE_TOKEN);
    return token;
  }

  public setAuthorizationToken(token: string) {
    localStorage.setItem(LS_CHAVE_TOKEN, token);
  }

  public getTokenExpirationDate(token: string): Date {
    const decoded: any = jwtDecode(token);

    const date = new Date(0);
    if (decoded.exp === undefined) {
      return date;
    }

    date.setUTCSeconds(decoded.exp);
    return date;
  }

  public isTokenExpired(token?: string): boolean {
    if (!token) {
      return true;
    }

    const date = this.getTokenExpirationDate(token);
    if (date === new Date(0)) {
      return false;
    }

    return date.valueOf() < new Date().valueOf();
  }

  public isUserLoggedIn() {
    const token = this.getAuthorizationToken();
    if (!token) {
      return false;
    }

    if (this.isTokenExpired(token)) {
      return false;
    }

    return true;
  }
}
