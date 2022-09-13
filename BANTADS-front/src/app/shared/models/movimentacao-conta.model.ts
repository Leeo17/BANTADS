import { TipoMovimentacao } from '../enums/tipo-movimentacao.enum';

export class MovimentacaoConta {
  constructor(
    public id?: string,
    public dataHora?: Date,
    public tipo?: TipoMovimentacao,
    public numeroContaOrigem?: number,
    public numeroContaDestino?: number,
    public nomeClienteOrigem?: string,
    public nomeClienteDestino?: string,
    public valorMovimentacao?: number,
    public saldoAposMovimentacao?: number,
  ) { }
}
