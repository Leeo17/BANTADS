import { TipoPerfil } from '../enums';

export class Usuario {
  constructor(
    public id?: string,
    public nome?: string,
    public login?: string,
    public senha?: string,
    public perfil?: TipoPerfil,
  ) { }
}
