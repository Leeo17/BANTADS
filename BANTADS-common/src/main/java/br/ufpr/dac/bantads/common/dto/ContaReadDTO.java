package br.ufpr.dac.bantads.common.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public class ContaReadDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long numero;
    private Date dataCriacao;
    private BigDecimal limite;
    private BigDecimal saldo;
    private Boolean aprovada;
    private String motivoReprovacao;
    private Date dataHoraAprovacaoReprovacao;
    private UUID idGerente;
    private UUID idCliente;
    private String nomeCliente;
    private String emailCliente;
    private String cpfCliente;
    private BigDecimal salarioCliente;
    private String cidadeCliente;
    private String estadoCliente;
    private String tipoEnderecoCliente;
    private String logradouroCliente;
    private String complementoEnderecoCliente;
    private String cepCliente;


    public ContaReadDTO() {
        super();
    }

    public ContaReadDTO(Long numero, Date dataCriacao, BigDecimal limite, BigDecimal saldo, Boolean aprovada,
                        String motivoReprovacao, Date dataHoraAprovacaoReprovacao, UUID idGerente, UUID idCliente,
                        String nomeCliente, String emailCliente, String cpfCliente, BigDecimal salarioCliente,
                        String cidadeCliente, String estadoCliente, String tipoEnderecoCliente, String logradouroCliente,
                        String complementoEnderecoCliente, String cepCliente) {
        this.numero = numero;
        this.dataCriacao = dataCriacao;
        this.limite = limite;
        this.saldo = saldo;
        this.aprovada = aprovada;
        this.motivoReprovacao = motivoReprovacao;
        this.dataHoraAprovacaoReprovacao = dataHoraAprovacaoReprovacao;
        this.idGerente = idGerente;
        this.idCliente = idCliente;
        this.nomeCliente = nomeCliente;
        this.emailCliente = emailCliente;
        this.cpfCliente = cpfCliente;
        this.salarioCliente = salarioCliente;
        this.cidadeCliente = cidadeCliente;
        this.estadoCliente = estadoCliente;
        this.tipoEnderecoCliente = tipoEnderecoCliente;
        this.logradouroCliente = logradouroCliente;
        this.complementoEnderecoCliente = complementoEnderecoCliente;
        this.cepCliente = cepCliente;
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

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public BigDecimal getSalarioCliente() {
        return salarioCliente;
    }

    public void setSalarioCliente(BigDecimal salarioCliente) {
        this.salarioCliente = salarioCliente;
    }

    public String getCidadeCliente() {
        return cidadeCliente;
    }

    public void setCidadeCliente(String cidadeCliente) {
        this.cidadeCliente = cidadeCliente;
    }

    public String getEstadoCliente() {
        return estadoCliente;
    }

    public void setEstadoCliente(String estadoCliente) {
        this.estadoCliente = estadoCliente;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getTipoEnderecoCliente() {
        return tipoEnderecoCliente;
    }

    public void setTipoEnderecoCliente(String tipoEnderecoCliente) {
        this.tipoEnderecoCliente = tipoEnderecoCliente;
    }

    public String getLogradouroCliente() {
        return logradouroCliente;
    }

    public void setLogradouroCliente(String logradouroCliente) {
        this.logradouroCliente = logradouroCliente;
    }

    public String getComplementoEnderecoCliente() {
        return complementoEnderecoCliente;
    }

    public void setComplementoEnderecoCliente(String complementoEnderecoCliente) {
        this.complementoEnderecoCliente = complementoEnderecoCliente;
    }

    public String getCepCliente() {
        return cepCliente;
    }

    public void setCepCliente(String cepCliente) {
        this.cepCliente = cepCliente;
    }
}
