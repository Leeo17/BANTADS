
export class ContaRead {
  constructor(
    public idCliente?: string,
    public numero?: number,
    public dataCriacao?: Date,
    public saldo?: number,
    public limite?: number,
    public aprovada?: boolean,
    public nomeCliente?: string,
    public emailCliente?: string,
    public cpfCliente?: string,
    public salarioCliente?: number,
    public cidadeCliente?: string,
    public estadoCliente?: string,
    public tipoEnderecoCliente?: string,
    public logradouroCliente?: string,
    public complementoEnderecoCliente?: string,
    public cepCliente?: string,
  ) { }
}
