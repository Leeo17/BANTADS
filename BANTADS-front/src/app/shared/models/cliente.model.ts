import { Conta } from './conta.model';
import { EnderecoCliente } from './endereco-cliente.model';

export class Cliente {
  constructor(
    public id?: string,
    public nome?: string,
    public email?: string,
    public cpf?: string,
    public endereco?: EnderecoCliente,
    public status?: boolean,
    public salario?: number,
    public conta?: Conta
  ) {
  }
}
