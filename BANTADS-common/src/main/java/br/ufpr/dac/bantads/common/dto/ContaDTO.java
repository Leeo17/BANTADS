package br.ufpr.dac.bantads.common.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public class ContaDTO implements Serializable {
    private Long numero;
    private UUID idCliente;
    private String nomeCliente;
    private Date dataCriacao;
    private BigDecimal limite;
    private BigDecimal saldo;
    private Boolean aprovada;
    private String motivoReprovacao;
    private Date dataHoraAprovacaoReprovacao;
    private UUID idGerente;

    public ContaDTO() {
        super();
    }

    public ContaDTO(Long numero, UUID idCliente, String nomeCliente, Date dataCriacao, BigDecimal limite,
                    BigDecimal saldo, Boolean aprovada, String motivoReprovacao, Date dataHoraAprovacaoReprovacao,
                    UUID idGerente) {
        this.numero = numero;
        this.idCliente = idCliente;
        this.nomeCliente = nomeCliente;
        this.dataCriacao = dataCriacao;
        this.limite = limite;
        this.saldo = saldo;
        this.aprovada = aprovada;
        this.motivoReprovacao = motivoReprovacao;
        this.dataHoraAprovacaoReprovacao = dataHoraAprovacaoReprovacao;
        this.idGerente = idGerente;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public UUID getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(UUID idCliente) {
        this.idCliente = idCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public void setLimite(BigDecimal limite) {
        this.limite = limite;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public Boolean getAprovada() {
        return aprovada;
    }

    public void setAprovada(Boolean aprovada) {
        this.aprovada = aprovada;
    }

    public String getMotivoReprovacao() {
        return motivoReprovacao;
    }

    public void setMotivoReprovacao(String motivoReprovacao) {
        this.motivoReprovacao = motivoReprovacao;
    }

    public Date getDataHoraAprovacaoReprovacao() {
        return dataHoraAprovacaoReprovacao;
    }

    public void setDataHoraAprovacaoReprovacao(Date dataHoraAprovacaoReprovacao) {
        this.dataHoraAprovacaoReprovacao = dataHoraAprovacaoReprovacao;
    }

    public UUID getIdGerente() {
        return idGerente;
    }

    public void setIdGerente(UUID idGerente) {
        this.idGerente = idGerente;
    }
}
