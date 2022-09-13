export class EnderecoCliente {
  constructor(
    public id?: string,
    public tipo?: string,
    public logradouro?: string,
    public numero?: number,
    public complemento?: string,
    public cep?: string,
    public cidade?: string,
    public estado?: string,
  ) {
  }
}
