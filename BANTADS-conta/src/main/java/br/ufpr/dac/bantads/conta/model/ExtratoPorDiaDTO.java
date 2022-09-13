package br.ufpr.dac.bantads.conta.model;

import java.math.BigDecimal;
import java.util.List;

public class ExtratoPorDiaDTO {
    private String dataFormatada;
    private BigDecimal saldo;
    private List<HistoricoMovimentacaoDTO> movimentacoes;

    public ExtratoPorDiaDTO() {
        super();
    }

    public ExtratoPorDiaDTO(String dataFormatada, BigDecimal saldo, List<HistoricoMovimentacaoDTO> movimentacoes) {
        this.dataFormatada = dataFormatada;
        this.saldo = saldo;
        this.movimentacoes = movimentacoes;
    }

    public String getDataFormatada() {
        return dataFormatada;
    }

    public void setDataFormatada(String dataFormatada) {
        this.dataFormatada = dataFormatada;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public List<HistoricoMovimentacaoDTO> getMovimentacoes() {
        return movimentacoes;
    }

    public void setMovimentacoes(List<HistoricoMovimentacaoDTO> movimentacoes) {
        this.movimentacoes = movimentacoes;
    }
}
