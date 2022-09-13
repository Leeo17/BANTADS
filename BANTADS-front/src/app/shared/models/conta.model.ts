
export class Conta {
  constructor(
    public idCliente?: string,
    public numero?: number,
    public dataCriacao?: Date,
    public saldo?: number,
    public limite?: number,
    public aprovada?: boolean,
  ) { }
}
