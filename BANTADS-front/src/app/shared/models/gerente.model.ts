
export class Gerente {
  constructor(
    public id?: string,
    public nome?: string,
    public email?: string,
    public cpf?: string,
    public quantidadeContas?: number,
    public somaSaldosPositivos?: number,
    public somaSaldosNegativos?: number,
  ) {
  }
}
