import { MovimentacaoConta } from './movimentacao-conta.model';

export class ExtratosPorDia {
  constructor(
    public dia?: Date,
    public saldo?: number,
    public movimentacoes?: MovimentacaoConta[],
  ) {
  }
}
