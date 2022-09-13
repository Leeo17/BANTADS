package br.ufpr.dac.bantads.conta.model;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public class HistoricoMovimentacaoDTO implements Serializable {
    private UUID id;
    private Date dataHora;
    private String tipo;
    private Long numeroContaOrigem;
    private Long numeroContaDestino;
    private String nomeClienteOrigem;
    private String nomeClienteDestino;
    private BigDecimal valorMovimentacao;
    private BigDecimal saldoAposMovimentacao;

    public HistoricoMovimentacaoDTO() {
        super();
    }

    public HistoricoMovimentacaoDTO(UUID id, Date dataHora, String tipo, Long numeroContaOrigem, Long numeroContaDestino,
                                    String nomeClienteOrigem, String nomeClienteDestino, BigDecimal valorMovimentacao,
                                    BigDecimal saldoAposMovimentacao) {
        this.id = id;
        this.dataHora = dataHora;
        this.tipo = tipo;
        this.numeroContaOrigem = numeroContaOrigem;
        this.numeroContaDestino = numeroContaDestino;
        this.nomeClienteOrigem = nomeClienteOrigem;
        this.nomeClienteDestino = nomeClienteDestino;
        this.valorMovimentacao = valorMovimentacao;
        this.saldoAposMovimentacao = saldoAposMovimentacao;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Long getNumeroContaOrigem() {
        return numeroContaOrigem;
    }

    public void setNumeroContaOrigem(Long numeroContaOrigem) {
        this.numeroContaOrigem = numeroContaOrigem;
    }

    public Long getNumeroContaDestino() {
        return numeroContaDestino;
    }

    public void setNumeroContaDestino(Long numeroContaDestino) {
        this.numeroContaDestino = numeroContaDestino;
    }

    public String getNomeClienteOrigem() {
        return nomeClienteOrigem;
    }

    public void setNomeClienteOrigem(String nomeClienteOrigem) {
        this.nomeClienteOrigem = nomeClienteOrigem;
    }

    public String getNomeClienteDestino() {
        return nomeClienteDestino;
    }

    public void setNomeClienteDestino(String nomeClienteDestino) {
        this.nomeClienteDestino = nomeClienteDestino;
    }

    public BigDecimal getValorMovimentacao() {
        return valorMovimentacao;
    }

    public void setValorMovimentacao(BigDecimal valorMovimentacao) {
        this.valorMovimentacao = valorMovimentacao;
    }

    public BigDecimal getSaldoAposMovimentacao() {
        return saldoAposMovimentacao;
    }

    public void setSaldoAposMovimentacao(BigDecimal saldoAposMovimentacao) {
        this.saldoAposMovimentacao = saldoAposMovimentacao;
    }
}
